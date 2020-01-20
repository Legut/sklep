package dao;

import objects.CartProduct;
import objects.Product;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartsDAO {
    public static boolean insertCartsContent(String user_id, ArrayList<CartProduct> list) {
        if (user_id != null && list!=null && list.size()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    String statement = "INSERT INTO carts (user_id, product_id, amount) VALUES";
                    for(int i = 0; i < list.size(); i++){
                        if(i==list.size()-1){
                            statement += " (" + user_id + ", " + list.get(i).getProduct().getId() + ", " + list.get(i).getQuantity() + ")";
                        } else {
                            statement += " (" + user_id + ", " + list.get(i).getProduct().getId() + ", " + list.get(i).getQuantity() + "),";
                        }
                    }
                    statement += " ON DUPLICATE KEY UPDATE user_id=VALUES(user_id), product_id=VALUES(product_id), amount=VALUES(amount)";
                    ps = con.prepareStatement(statement);
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; CartsDAO.insertCartsContent() --> " + ex.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Adding product error while closing database connection or prepared statement; CartsDAO.insertCartsContent() --> " + ex.getMessage());
                } finally {
                    return true;
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; CartsDAO.insertCartsContent()");
            return false;
        }
    }
    public static boolean handleCartDestroy(String user_id, Map<Long, CartProduct> newCart) {
        // It's needed to check if there are all information provided to execute further operations
        if (user_id != null) {
            // To handle the cart destroy the oldCart is also needed. You need to check if there are some
            // products in old cart, which doesn't exist in the new one.

            Map<Long, CartProduct> oldCart = CartsDAO.getCartsContent(user_id);
            PreparedStatement ps = null;
            Connection con = null;
            boolean firstMatch = true;

            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    StringBuilder statement = new StringBuilder();

                    if(newCart==null) {
                        statement = new StringBuilder("DELETE FROM carts WHERE user_id = " + user_id);
                        ps = con.prepareStatement(statement.toString());
                        ps.executeUpdate();
                    } else {
                        // iterate through oldCart to find products, which doesn't exist in the newCart
                        if(oldCart!=null) {
                            for (Map.Entry<Long, CartProduct> entry : oldCart.entrySet()) {
                                if (newCart.get(entry.getKey()) == null) {
                                    if (firstMatch) {
                                        // It's a match! First match means, that there is something to delete. Start building the
                                        // DELETE statement by building complex string query. Also change flag, so the base of
                                        // query will never be added to statement again.
                                        statement = new StringBuilder("DELETE FROM carts WHERE user_id = " + user_id + " AND product_id IN (");
                                        firstMatch = false;
                                    }
                                    // add id to DELETE query one by one
                                    statement.append(entry.getKey()).append(",");
                                }
                            }

                            if (!firstMatch) {
                                // It means, that there was a match and the flag was changed. After all products id's needed to be deleted
                                // are already in the statement, you need to delete last comma and close the statement.
                                statement = new StringBuilder(statement.substring(0, statement.length() - 1));
                                statement.append("); ");

                                // Execute the delete statement
                                ps = con.prepareStatement(statement.toString());
                                ps.executeUpdate();
                            }
                        }
                    }

                    if(newCart!=null && newCart.size()>0) {
                        // After executing DELETE query (or not), you have to take care of those products, which exist in both carts
                        // or exists only in the new cart. So you will be inserting values or creating new rows ON DUPLICATE case.
                        // This will prevent creating similar rows with same product_id value. Base for the statement:
                        statement = new StringBuilder("INSERT INTO carts (user_id, product_id, amount) VALUES");

                        // Than you need to iterate through new cart and put all values in statement. It's not worth to check for changes,
                        // in order to prevent querying if there are no differences. It would take equal time to check it and there wouldn't be
                        // many beneficial cases. So you just build the query:
                        for (Map.Entry<Long, CartProduct> entry : newCart.entrySet()) {
                            statement.append(" (").append(user_id).append(", ").append(entry.getKey()).append(", ").append(entry.getValue().getQuantity()).append("),");
                        }

                        // After all products id's needed to be updated are already in the statement, you need to delete last comma and
                        // close the statement.
                        statement = new StringBuilder(statement.substring(0, statement.length() - 1));
                        System.out.println(statement);
                        statement.append(" ON DUPLICATE KEY UPDATE user_id=VALUES(user_id), product_id=VALUES(product_id), amount=VALUES(amount);");

                        // Execute the final statement.
                        ps = con.prepareStatement(statement.toString());
                        ps.executeUpdate();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; CartsDAO.handleCartDestroy() --> " + ex.getMessage());
                return false;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; CartsDAO.handleCartDestroy() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; CartsDAO.handleCartDestroy()");
            return false;
        }
        return true;
    }
    public static Map<Long, CartProduct> getCartsContent(String userId) {
        Connection con = null;
        PreparedStatement ps = null;
        Map<Long, CartProduct> productsList = new HashMap<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT C.user_id, C.product_id, C.amount, P.product_name, P.category_id, P.quantity, P.quantity_sold, P.sale_price," +
                    " P.date_added, P.price, P.description, P.photo_link_one, P.photo_link_two, P.photo_link_three, P.photo_link_four, P.featured, CAT.category_name" +
                    " FROM carts AS C LEFT JOIN products AS P ON C.product_id = P.product_id LEFT JOIN categories AS CAT ON P.category_id = CAT.category_id WHERE C.user_id = ? ORDER BY C.user_id, C.product_id");
            ps.setString(1, userId);
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
                CartProduct temporary = new CartProduct(temp, rs.getInt("amount"));
                productsList.put(temp.getId(), temporary);
            }

        } catch (SQLException ex) {
            System.out.println("Error while getting carts data from db; CartsDAO.getCartsContent() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Error while closing database connection or prepared statement; CartsDAO.getCartsContent() -->" + ex.getMessage()); }
        }
        return productsList;
    }
    public static boolean isAnythingInCart(String userId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM carts WHERE user_id = ?");
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error while checking carts data from db; CartsDAO.isAnythingInCart() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Error while closing database connection or prepared statement; CartsDAO.isAnythingInCart() -->" + ex.getMessage()); }
        }
        return false;
    }
    public static boolean bookGivenAmount(Product product, Integer quantity) {
        if (product != null && quantity != null && quantity > 0) {
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("UPDATE products SET quantity = quantity - ? WHERE product_id = ?");
                ps.setInt(1, quantity);
                ps.setLong(2, product.getId());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error while changing carts data from db; CartsDAO.bookGivenAmount() -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
                try {
                    ps.close();
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; CartsDAO.bookGivenAmount() -->" + ex.getMessage());
                }
            }
        }
        return false;
    }
    public static boolean unbookGivenAmount(Product product, Integer quantity) {
        if (product != null && quantity != null && quantity > 0) {
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("UPDATE products SET quantity = quantity + ? WHERE product_id = ?");
                ps.setInt(1, quantity);
                ps.setLong(2, product.getId());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error while changing carts data from db; CartsDAO.unbookGivenAmount() -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
                try {
                    ps.close();
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; CartsDAO.unbookGivenAmount() -->" + ex.getMessage());
                }
            }
        }
        return false;
    }
    public static ArrayList<CartProduct> getSpecificProducts(Map<Long, CartProduct> productsMap) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<CartProduct> cartProducts = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            StringBuilder str = new StringBuilder("SELECT * FROM products LEFT JOIN categories ON products.category_id = categories.category_id WHERE product_id IN (");
            int i = 0;
            for(Map.Entry<Long,CartProduct> entry: productsMap.entrySet()) {
                if(i == productsMap.size()-1) {
                    str.append(entry.getKey());
                } else {
                    str.append(entry.getKey()).append(",");
                }
                i++;
            }
            str.append(") ORDER BY products.product_id");
            ps = con.prepareStatement(str.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product tempProduct = new Product(rs.getInt("product_id"),
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
                Integer tempQuantity = productsMap.get(tempProduct.getId()).getQuantity();
                CartProduct cartTemp = new CartProduct(tempProduct, tempQuantity);
                cartProducts.add(cartTemp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; CartsDAO.getSpecificProducts() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; CartsDAO.getSpecificProducts() -->" + ex.getMessage()); }
        }
        return cartProducts;
    }
    /*public static boolean unbookWholeCart(Map<Long, CartProduct> cartProductsMap) {
        if (cartProductsMap != null && cartProductsMap.size() > 0) {
            Connection con = null;
            PreparedStatement ps = null;

            String str = "INSERT INTO products (product_id, quantity) VALUES";
            for(Map.Entry<Long, CartProduct> entry: cartProductsMap.entrySet()) {
                if(entry.getValue().getQuantityBooked() > 0) {
                    str += " (" + entry.getKey() + ", quantity + " + entry.getValue().getQuantityBooked() + "), ";
                }
            }
            str = str.substring(0, str.length() - 2);
            str += " ON DUPLICATE KEY UPDATE quantity=VALUES(quantity)";

            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement(str);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error while unbooking carts data in db; CartsDAO.unbookWholeCart() -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
                try {
                    ps.close();
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; CartsDAO.unbookWholeCart() -->" + ex.getMessage());
                }
            }
        }
        return false;
    }*/
}
