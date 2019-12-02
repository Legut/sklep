package dao;

import objects.Product;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    public static void addProduct(String product_name, String category, int quantity, boolean on_sale, double price, String description, int gallery_id) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = DataConnect.getConnection();
            if (con != null) {
                String sql = "INSERT INTO products(product_name, category, quantity, on_sale, price, description, gallery_id, date_added) VALUES(?,?,?,?,?,?,?,NOW())";
                ps = con.prepareStatement(sql);
                ps.setString(1, product_name);
                ps.setString(2, category);
                ps.setInt(3, quantity);
                ps.setBoolean(4, on_sale);
                ps.setDouble(5, price);
                ps.setString(6, description);
                ps.setInt(7, gallery_id);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Product add error when executing query; ProductDAO.addProduct() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.addProduct() -->" + ex.getMessage()); }
        }
    }
    public static Product getProduct(long id) {
        PreparedStatement ps = null;
        Connection con = null;
        Product product = null;
        try {
            con = DataConnect.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM products WHERE product_id=?";
                ps = con.prepareStatement(sql);
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("category"),
                            rs.getInt("quantity"),
                            rs.getInt("quantity_sold"),
                            rs.getBoolean("on_sale"),
                            rs.getString("date_added"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("gallery_id"));
                }
            }
        } catch (Exception ex) {
            System.out.println("Product request error when executing query; ProductDAO.requestProductName() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.addProduct() -->" + ex.getMessage()); }
        }
        return product;
    }
    public static long amountOfProducts() {
        Connection con = null;
        long amount = 0;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS `amount` FROM products");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getLong("amount");
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting product data from db; ProductDAO.amountOfProducts() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.addProduct() -->" + ex.getMessage()); }
        }
        return amount;
    }
    public static ArrayList<Product> getProductsList(long startPosition, long amount) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products ORDER BY category LIMIT ?, ?");
            ps.setLong(1, startPosition);
            ps.setLong(2, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product temp = new Product(rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getInt("quantity_sold"),
                        rs.getBoolean("on_sale"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("gallery_id"));
                productsList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getProductsList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.addProduct() -->" + ex.getMessage()); }
        }
        return productsList;
    }
    public static boolean deleteSingleProduct(String deleteId) {
        Connection con = null;
        PreparedStatement ps = null;
        if(checkIfProductExists(deleteId)) {
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("DELETE FROM products WHERE product_id = ?");
                ps.setString(1, deleteId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error while deleting product from db; ProductDAO.deleteSingleProduct() -->" + ex.getMessage());
                return false;
            } finally {
                DataConnect.close(con);
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Error while closing PreparedStatement; ProductDAO.deleteSingleProduct() -->" + ex.getMessage());
                    }
                }
            }
            return true;
        } else {
            System.out.println("Product with given ID doesn't exist; ProductDAO.deleteSingleProduct()");
            return false;
        }
    }
    public static boolean checkIfProductExists(String id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error while checking if product exists in db; ProductDAO.checkIfProductExists() -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error while closing PreparedStatement; ProductDAO.checkIfProductExists() -->" + ex.getMessage());
                }
            }
        }
        return false;
    }
    public static ArrayList<Product> getProductListOfPattern(long startPosition, long amountPerPage, String searchByProductName, int searchOption) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products WHERE product_name LIKE ? ORDER BY product_id LIMIT ?, ?");
            if(searchOption==1){
                ps.setString(1, searchByProductName + "%");
            } else if(searchOption==3) {
                ps.setString(1, "%" + searchByProductName);
            } else {
                ps.setString(1, "%" + searchByProductName + "%");
            }
            ps.setLong(2, startPosition);
            ps.setLong(3, amountPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product temp = new Product(rs.getLong("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getLong("quantity"),
                        rs.getLong("quantity_sold"),
                        rs.getBoolean("on_sale"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getLong("gallery_id"));
                productList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getProductsListOfPattern() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) { try { ps.close(); } catch (SQLException ex) { System.out.println("Error while closing PreparedStatement; ProductDAO.getProductsListOfPattern() -->" + ex.getMessage()); } }
        }
        return productList;
    }
    public static long amountOfProductsOfPattern(String pattern, int searchOption) {
        Connection con = null;
        long amount = 0;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS `amount` FROM products WHERE product_name LIKE ?");
            if(searchOption==1){
                ps.setString(1, pattern + "%"); //zaczyna się na
            } else if(searchOption==3) {
                ps.setString(1, "%" + pattern); //kończy się na
            } else {
                ps.setString(1, "%" + pattern + "%"); //zawiera
            }
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                amount = rs.getLong("amount");
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.amountOfProductsOfPattern() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error while closing PreparedStatement; ProductDAO.amountOfProductsOfPattern() -->" + ex.getMessage());
                }
            }
        }
        return amount;
    }
    public static Product getSingleProductData(String productId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product singleProduct = new Product(rs.getLong("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getLong("quantity"),
                        rs.getLong("quantity_sold"),
                        rs.getBoolean("on_sale"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getLong("gallery_id"));
                return singleProduct;
            }
        } catch (SQLException ex) {
            System.out.println("Error while checking if product exists in db; ProductDAO.getSingleProductData() -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error while closing PreparedStatement; ProductDAO.getSingleProductData() -->" + ex.getMessage());
                }
            }
        }
        return null;
    }
    public static boolean editGivenProduct(String product_id, String product_name, String category, String quantity, String quantity_sold, String on_sale, String date_added, String price, String description, String gallery_id) {
        if (product_id != null || product_name != null || category != null) {
            PreparedStatement ps = null;
            Connection con = null;

            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    ps = con.prepareStatement("UPDATE products SET product_name = ?, category = ?, quantity = ?, quantity_sold = ?, on_sale = ?, date_added = ?, price = ?, description = ?, gallery_id = ? WHERE product_id = ? ");
                    ps.setString(1, product_name);
                    ps.setString(2, category);
                    ps.setString(3, quantity);
                    ps.setString(4, quantity_sold);
                    ps.setString(5, on_sale);
                    ps.setString(6, date_added);
                    ps.setString(7, price);
                    ps.setString(8, description);
                    ps.setString(9, gallery_id);
                    ps.setString(10, product_id);
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Error while updating user data; ProductDAO.editGivenProduct() -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
                if (ps != null) { try { ps.close(); } catch (SQLException ex) { System.out.println("Error while closing PreparedStatement; ProductDAO.editGivenProduct() -->" + ex.getMessage()); } }
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean addProduct(String product_name, String category, String quantity, String quantity_sold, String on_sale, String price, String description, String gallery_id) {
        if (product_name != null || category != null || quantity != null) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    String sql = "INSERT INTO users(product_name, category, quantity, quantity_sold, on_sale, date_added, price, description, gallery_id) VALUES(?,?,?,?,?,NOW(),?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, product_name);
                    ps.setString(2, category);
                    ps.setString(3, quantity);
                    ps.setString(4, quantity_sold);
                    ps.setString(5, on_sale);
                    ps.setString(6, price);
                    ps.setString(7, description);
                    ps.setString(8, gallery_id);
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Registration error when executing query; ProductDAO.addProduct() -->" + ex.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Adding product error when closing database connection or prepared statement; ProductDAO.addProduct() -->" + ex.getMessage());
                } finally {
                    return true;
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; ProductDAO.addProduct() -->");
            return false;
        }
    }
}