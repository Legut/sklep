package dao;

import objects.Product;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    public static void addProduct(String name, String category, int quantity, boolean on_sale, double price, String description, int gallery_id) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = DataConnect.getConnection();
            if (con != null) {
                String sql = "INSERT INTO products(name, category, quantity, on_sale, price, description, gallery_id, date_added) VALUES(?,?,?,?,?,?,?,NOW())";
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
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
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                System.out.println("Product add error when closing database connection or prepared statement; ProductDAO.addProduct() -->" + ex.getMessage());
            }
        }
    }

    public static void requestProductParameter(String parameter, int id) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = DataConnect.getConnection();
            if (con != null) {
                String sql = "SELECT ? FROM products WHERE product_id=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, parameter);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Product request error when executing query; ProductDAO.requestProductName() -->" + ex.getMessage());
        } finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                System.out.println("Product request error when closing database connection or prepared statement; ProductDAO.requestProductName() -->" + ex.getMessage());
            }
        }
    }

    public static void deleteProduct(int id) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = DataConnect.getConnection();
            if (con != null) {
                String sql = "DELETE FROM products WHERE product_id=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Product delete error when executing query; ProductDAO.addProduct() -->" + ex.getMessage());
        } finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.addProduct() -->" + ex.getMessage());
            }
        }
    }

    public static long amountOfProducts() {
        Connection con = null;
        long amount = 0;
        PreparedStatement ps;

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
        }
        return amount;
    }

    public static ArrayList<Product> getProductsList(long startPosition, long amount) {
        Connection con = null;
        PreparedStatement ps;
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM products ORDER BY category LIMIT ?, ?");
            ps.setLong(1, startPosition);
            ps.setLong(2, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product temp = new Product(rs.getInt("product_id"),
                        rs.getString("name"),
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
        }
        return productsList;
    }

}