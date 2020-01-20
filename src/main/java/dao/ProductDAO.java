package dao;

import objects.CartProduct;
import objects.Product;
import util.DataConnect;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    public static Product getProduct(long id) {
        PreparedStatement ps = null;
        Connection con = null;
        Product product = null;
        try {
            con = DataConnect.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM products LEFT JOIN categories ON products.category_id = categories.category_id WHERE products.product_id=?";
                ps = con.prepareStatement(sql);
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("category_name"),
                            rs.getInt("quantity"),
                            rs.getInt("quantity_sold"),
                            rs.getDouble("sale_price"),
                            rs.getString("date_added"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("photo_link_one"),
                            rs.getString("photo_link_two"),
                            rs.getString("photo_link_three"),
                            rs.getString("photo_link_four"),
                            rs.getBoolean("featured"));
                }
            }
        } catch (Exception ex) {
            System.out.println("Product request error when executing query; ProductDAO.getProduct() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getProduct() -->" + ex.getMessage()); }
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
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.amountOfProducts() -->" + ex.getMessage()); }
        }
        return amount;
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
    public static ArrayList<Product> getProductsList(long startPosition, long amount) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products LEFT JOIN categories ON products.category_id = categories.category_id ORDER BY products.product_id LIMIT ?, ?");
            ps.setLong(1, startPosition);
            ps.setLong(2, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product temp = new Product(rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getInt("quantity"),
                        rs.getInt("quantity_sold"),
                        rs.getDouble("sale_price"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("photo_link_one"),
                        rs.getString("photo_link_two"),
                        rs.getString("photo_link_three"),
                        rs.getString("photo_link_four"),
                        rs.getBoolean("featured"));
                productsList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getProductsList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getProductsList() -->" + ex.getMessage()); }
        }
        return productsList;
    }
    public static ArrayList<Product> getProductsListByCategory(long category_id, long startPosition, long amount) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products LEFT JOIN categories ON products.category_id = categories.category_id WHERE products.category_id = ? ORDER BY products.product_id LIMIT ?, ?");
            ps.setLong(1, category_id);
            ps.setLong(2, startPosition);
            ps.setLong(3, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product temp = new Product(rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getInt("quantity"),
                        rs.getInt("quantity_sold"),
                        rs.getDouble("sale_price"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("photo_link_one"),
                        rs.getString("photo_link_two"),
                        rs.getString("photo_link_three"),
                        rs.getString("photo_link_four"),
                        rs.getBoolean("featured"));
                productsList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getProductsList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getProductsList() -->" + ex.getMessage()); }
        }
        return productsList;
    }
    public static ArrayList<Product> getProductsListCustomStatement(String statement) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product temp = new Product(rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getInt("quantity"),
                        rs.getInt("quantity_sold"),
                        rs.getDouble("sale_price"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("photo_link_one"),
                        rs.getString("photo_link_two"),
                        rs.getString("photo_link_three"),
                        rs.getString("photo_link_four"),
                        rs.getBoolean("featured"));
                productsList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getProductsListCustomStatement() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getProductsListCustomStatement() -->" + ex.getMessage());
            }
        }
        return productsList;
    }
    public static long getAmountOfProductsCustomStatement(String statement) {
        Connection con = null;
        PreparedStatement ps = null;
        long amountOfProducts = 0;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amountOfProducts = rs.getLong("amountOfPages");
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getAmountOfProductsCustomStatement() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getAmountOfProductsCustomStatement() -->" + ex.getMessage());
            }
        }
        return amountOfProducts;
    }
    public static ArrayList<Product> getFeaturedProductsList(long amount) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products WHERE featured = ? ORDER BY product_id LIMIT ?");
            ps.setBoolean(1, true);
            ps.setLong(2, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product temp = new Product(rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_id"),
                        rs.getInt("quantity"),
                        rs.getInt("quantity_sold"),
                        rs.getDouble("sale_price"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("photo_link_one"),
                        rs.getString("photo_link_two"),
                        rs.getString("photo_link_three"),
                        rs.getString("photo_link_four"),
                        rs.getBoolean("featured"));
                productsList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getFeaturedProductsList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getFeaturedProductsList() -->" + ex.getMessage()); }
        }
        return productsList;
    }
    public static JsonArray getProductsList(String statement) {
        Connection con = null;
        PreparedStatement ps = null;
        JsonArray productsList = null;

        JsonArrayBuilder builder = Json.createArrayBuilder();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                builder.add(Json.createObjectBuilder()
                        .add("product_id", rs.getString("product_id"))
                        .add("product_name", rs.getString("product_name"))
                        .add("category_id", rs.getString("category_id"))
                        .add("quantity", rs.getString("quantity"))
                        .add("quantity_sold", rs.getString("quantity_sold"))
                        .add("sale_price", rs.getString("sale_price"))
                        .add("date_added", rs.getString("date_added"))
                        .add("price", rs.getString("price"))
                        .add("description",  rs.getString("description"))
                        .add("photo_link_one", rs.getString("photo_link_one"))
                        .add("photo_link_two", rs.getString("photo_link_two"))
                        .add("photo_link_three", rs.getString("photo_link_three"))
                        .add("photo_link_four", rs.getString("photo_link_four"))
                        .add("featured", rs.getString("featured")));
            }
            productsList = builder.build();
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getProductsList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getProductsList() -->" + ex.getMessage()); }
        }
        return productsList;
    }
    public static ArrayList<Product> getProductListOfPattern(long startPosition, long amountPerPage, String searchByProductName, int searchOption) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Product> productList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products LEFT JOIN categories ON products.category_id = categories.category_id WHERE products.product_name LIKE ? ORDER BY products.product_id LIMIT ?, ?");
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
                        rs.getString("category_name"),
                        rs.getLong("quantity"),
                        rs.getLong("quantity_sold"),
                        rs.getDouble("sale_price"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("photo_link_one"),
                        rs.getString("photo_link_two"),
                        rs.getString("photo_link_three"),
                        rs.getString("photo_link_four"),
                        rs.getBoolean("featured"));
                productList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getProductListOfPattern() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) { try { ps.close(); } catch (SQLException ex) { System.out.println("Error while closing PreparedStatement; ProductDAO.getProductListOfPattern() -->" + ex.getMessage()); } }
        }
        return productList;
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
    public static Product getSingleProductData(String productId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products LEFT JOIN categories ON products.category_id = categories.category_id WHERE products.product_id = ?");
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product singleProduct = new Product(rs.getLong("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getLong("quantity"),
                        rs.getLong("quantity_sold"),
                        rs.getDouble("sale_price"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("photo_link_one"),
                        rs.getString("photo_link_two"),
                        rs.getString("photo_link_three"),
                        rs.getString("photo_link_four"),
                        rs.getBoolean("featured"));
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
    public static Product getSingleProductDataForCart(String productId, Integer quantity) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products LEFT JOIN categories ON products.category_id = categories.category_id WHERE products.product_id = ?");
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product singleProduct = new Product(rs.getLong("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category_name"),
                        rs.getLong("quantity"),
                        rs.getLong("quantity_sold"),
                        rs.getDouble("sale_price"),
                        rs.getString("date_added"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("photo_link_one"),
                        rs.getString("photo_link_two"),
                        rs.getString("photo_link_three"),
                        rs.getString("photo_link_four"),
                        rs.getBoolean("featured"));
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
    public static boolean editGivenProduct(String product_id, String product_name, String category_id, String quantity, String quantity_sold, String sale_price,
                                           String date_added, String price, String description, String photoLinkOne, String photoLinkTwo, String photoLinkThree, String photoLinkFour, String featured) {
        if (product_id != null || product_name != null || category_id != null) {
            PreparedStatement ps = null;
            Connection con = null;

            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    ps = con.prepareStatement("UPDATE products SET product_name = ?, category_id = ?, quantity = ?, quantity_sold = ?, sale_price = ?, date_added = ?, " +
                            "price = ?, description = ?, photo_link_one = ?, photo_link_two = ?, photo_link_three = ?, photo_link_four = ?, featured = ? WHERE product_id = ? ");
                    ps.setString(1, product_name);
                    ps.setString(2, category_id);
                    ps.setString(3, quantity);
                    ps.setString(4, quantity_sold);
                    ps.setString(5, sale_price);
                    ps.setString(6, date_added);
                    ps.setString(7, price);
                    ps.setString(8, description);
                    ps.setString(9, photoLinkOne);
                    ps.setString(10, photoLinkTwo);
                    ps.setString(11, photoLinkThree);
                    ps.setString(12, photoLinkFour);
                    int boolToInt;
                    if(featured.equals("true")) { boolToInt = 1; }
                    else { boolToInt = 0; }
                    ps.setString(13, String.valueOf(boolToInt));
                    ps.setString(14, product_id);
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
    public static boolean addProduct(String product_name, String category_id, String quantity, String quantity_sold, String sale_price, String date_added, String price,
                                     String description, String photoLinkOne, String photoLinkTwo, String photoLinkThree, String photoLinkFour, String featured) {
        if (product_name != null || category_id != null || quantity != null) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    ps = con.prepareStatement("INSERT INTO products (product_name, category_id, quantity, quantity_sold, sale_price, date_added, price, " +
                            "description, photo_link_one, photo_link_two, photo_link_three, photo_link_four, featured) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, product_name);
                    ps.setString(2, category_id);
                    ps.setString(3, quantity);
                    ps.setString(4, quantity_sold);
                    ps.setString(5, sale_price);
                    ps.setString(6, date_added);
                    ps.setString(7, price);
                    ps.setString(8, description);
                    ps.setString(9, photoLinkOne);
                    ps.setString(10, photoLinkTwo);
                    ps.setString(11, photoLinkThree);
                    ps.setString(12, photoLinkFour);
                    int boolToInt;
                    if(featured.equals("true")) { boolToInt = 1; }
                    else { boolToInt = 0; }
                    ps.setString(13, String.valueOf(boolToInt));
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