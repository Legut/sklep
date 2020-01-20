package dao;

import objects.CartProduct;
import objects.Product;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderDAO {
    public static boolean removeUnfinishedOrderContent(String user_id) {
        // It's needed to check if there are all information provided to execute further operations
        if (user_id != null && user_id.length()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    // First you need to check if there are some unfinished order info  in db
                    StringBuilder statement = new StringBuilder();
                    statement.append("SELECT * FROM orders WHERE user_id = ").append(user_id).append(" AND finished = 0");
                    ps = con.prepareStatement(statement.toString());
                    ResultSet rs = ps.executeQuery();

                    // There might be only one unfinished order, because every time you build second unfinished order,
                    // the old one is vanished
                    String orderId = null;
                    while(rs.next()) {
                        orderId = rs.getString("order_id");
                    }

                    // if there are some info about unfinished order than orderExists is no longer null.
                    if(orderId!=null) {
                        // you dont have to clear data for this order. That would multiply order_id value fast. Instead
                        // take care of content od this order, which is stored in orders_content table. The order info
                        // from orders table will be overwritten while creating new order. Let's build the statement:
                        statement = new StringBuilder("DELETE FROM orders_content WHERE order_id = " + orderId);
                        ps = con.prepareStatement(statement.toString());
                        ps.executeUpdate();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.removeUnfinishedOrderContent() --> " + ex.getMessage());
                return false;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.removeUnfinishedOrderContent() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.removeUnfinishedOrderContent()");
            return false;
        }
        return true;
    }
    public static boolean returnUnfinishedOrderContentToStock(String user_id) {
        // It's needed to check if there are all information provided to execute further operations
        if (user_id != null && user_id.length()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    // First you need to check if there are some unfinished order info  in db
                    StringBuilder statement = new StringBuilder();
                    statement.append("SELECT * FROM orders WHERE user_id = ").append(user_id).append(" AND finished = 0");
                    ps = con.prepareStatement(statement.toString());
                    ResultSet rs = ps.executeQuery();

                    // There might be only one unfinished order, because every time you build second unfinished order,
                    // the old one is vanished
                    String orderId = null;
                    while(rs.next()) {
                        orderId = rs.getString("order_id");
                    }

                    // if there are some info about unfinished order than orderExists is no longer null.
                    if(orderId!=null) {
                        // first you need to get data from orders_content
                        statement = new StringBuilder("SELECT * FROM orders_content WHERE order_id = " + orderId);
                        ps = con.prepareStatement(statement.toString());
                        rs = ps.executeQuery();
                        HashMap<String, String> ordersContent = new HashMap<>();

                        while(rs.next()) {
                            ordersContent.put(rs.getString("product_id"), rs.getString("quantity"));
                        }
                        // now ordersContent is filled with product Id's and their quantity. So now you can give those
                        // quantities back to products table

                        if(ordersContent.size()>0) {
                            for(Map.Entry<String, String> entry: ordersContent.entrySet()) {
                                statement = new StringBuilder("UPDATE products SET quantity = quantity + " + entry.getValue() + " WHERE product_id = " + entry.getKey());
                                ps = con.prepareStatement(statement.toString());
                                ps.executeUpdate();
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.returnUnfinishedOrderContentToStock() --> " + ex.getMessage());
                return false;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.returnUnfinishedOrderContentToStock() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.returnUnfinishedOrderContentToStock()");
            return false;
        }
        return true;
    }
    public static boolean removeUnfinishedOrderContentOrderId(String orderId) {
        // It's needed to check if there are all information provided to execute further operations
        if (orderId != null && orderId.length()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    // you dont have to clear data for this order. That would multiply order_id value fast. Instead
                    // take care of content od this order, which is stored in orders_content table. The order info
                    // from orders table will be overwritten while creating new order. Let's build the statement:
                    StringBuilder statement = new StringBuilder("DELETE FROM orders_content WHERE order_id = " + orderId);
                    ps = con.prepareStatement(statement.toString());
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.removeUnfinishedOrderContentOrderId() --> " + ex.getMessage());
                return false;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.removeUnfinishedOrderContentOrderId() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.removeUnfinishedOrderContentOrderId()");
            return false;
        }
        return true;
    }
    public static boolean returnUnfinishedOrderContentToStockOrderId(String orderId) {
        // It's needed to check if there are all information provided to execute further operations
        if (orderId != null && orderId.length()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    // first you need to get data from orders_content
                    StringBuilder statement = new StringBuilder("SELECT * FROM orders_content WHERE order_id = " + orderId);
                    ps = con.prepareStatement(statement.toString());
                    ResultSet rs = ps.executeQuery();
                    HashMap<String, String> ordersContent = new HashMap<>();

                    while(rs.next()) {
                        ordersContent.put(rs.getString("product_id"), rs.getString("quantity"));
                    }
                    // now ordersContent is filled with product Id's and their quantity. So now you can give those
                    // quantities back to products table

                    if(ordersContent.size()>0) {
                        for(Map.Entry<String, String> entry: ordersContent.entrySet()) {
                            statement = new StringBuilder("UPDATE products SET quantity = quantity + " + entry.getValue() + " WHERE product_id = " + entry.getKey());
                            ps = con.prepareStatement(statement.toString());
                            ps.executeUpdate();
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.returnUnfinishedOrderContentToStock() --> " + ex.getMessage());
                return false;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.returnUnfinishedOrderContentToStock() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.returnUnfinishedOrderContentToStock()");
            return false;
        }
        return true;
    }
    public static boolean prepareOrder(String user_id, Map<Long, CartProduct> cart) {
        // It's needed to check if there are all information provided to execute further operations
        if (user_id != null && cart!=null && cart.size()>0) {
            // To prepare the order you need to lower quantity values in database. That's because
            // from this moment those products on the final order info page cannot be ordered by other client
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    StringBuilder statement = new StringBuilder();
                    double totalPrice = 0;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = new Date();
                    String date_added = dateFormat.format(date);

                    // iterate through cart to build statement, which will decrease the quantity in stock in database
                    for(Map.Entry<Long, CartProduct> entry: cart.entrySet()){
                        long exceptedQuantity = entry.getValue().getProduct().getQuantity() - entry.getValue().getQuantity();
                        Product p = entry.getValue().getProduct();
                        totalPrice += entry.getValue().getProduct().getPrice() * entry.getValue().getQuantity();
                        statement = new StringBuilder("UPDATE products SET quantity = " + exceptedQuantity + " WHERE product_id = " + p.getId());

                        ps = con.prepareStatement(statement.toString());
                        ps.executeUpdate();
                    }

                    // After executing UPDATE queries in products table, you have to put data in orders table.
                    // First check if there are any unfinished orders. If there are some, you should update them
                    statement = new StringBuilder("SELECT order_id FROM orders WHERE");
                    statement.append(" user_id = ").append(user_id).append(" AND finished = 0");

                    ps = con.prepareStatement(statement.toString());
                    ResultSet rs = ps.executeQuery();

                    String orderId = null;
                    while(rs.next()) {
                        orderId = rs.getString("order_id");
                    }

                    if(orderId==null){
                        // So there are no unfinished orders for this user. Create main order info and execute the query.
                        statement = new StringBuilder("INSERT INTO orders (user_id, total_price, date_added, finished, paid) VALUES ");
                        statement.append("(").append(user_id).append(", ").append(totalPrice).append(", '").append(date_added).append("', ").append(0).append(", ").append(0).append(")");

                        ps = con.prepareStatement(statement.toString());
                        ps.executeUpdate();

                        // Now get the order id acquired when executing last query
                        statement = new StringBuilder("SELECT order_id FROM orders WHERE");
                        statement.append(" user_id = ").append(user_id).append(" AND finished = 0");

                        ps = con.prepareStatement(statement.toString());
                        rs = ps.executeQuery();


                        orderId = null;
                        while(rs.next()) {
                            orderId = rs.getString("order_id");
                        }
                    } else {
                        // There is some unfinished order, which means you only have to update values
                        statement = new StringBuilder("UPDATE orders SET total_price = " + totalPrice + ", date_added = '" + date_added + "' WHERE order_id = " + orderId);

                        ps = con.prepareStatement(statement.toString());
                        ps.executeUpdate();

                    }
                    // Than build another statement and put ordered products info, one by one,
                    // inside orders_content table, using the orderId, which you get from last query.
                    statement = new StringBuilder("INSERT INTO orders_content (order_id, product_id, quantity) VALUES ");

                    for(Map.Entry<Long, CartProduct> entry: cart.entrySet()) {
                        statement.append("(").append(orderId).append(", ").append(entry.getKey()).append(", ").append(entry.getValue().getQuantity()).append("), ");
                    }

                    // After that you need to delete last two signs to remove the space and comma.
                    // Than you can finish the statement and execute it.
                    statement = new StringBuilder(statement.substring(0, statement.length() - 2));
                    ps = con.prepareStatement(statement.toString());
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.prepareOrder() --> " + ex.getMessage());
                return false;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.prepareOrder() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.prepareOrder()");
            return false;
        }
        return true;
    }
    public static ArrayList<CartProduct> getUnfinishedOrderContent(String user_id) {
        // It's needed to check if there are all information provided to execute further operations
        if (user_id != null && user_id.length()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    // First you need to check if there are some unfinished order info in db
                    StringBuilder statement = new StringBuilder();
                    statement.append("SELECT * FROM orders WHERE user_id = ").append(user_id).append(" AND finished = 0");
                    ps = con.prepareStatement(statement.toString());
                    ResultSet rs = ps.executeQuery();

                    // There might be only one unfinished order, because every time you build second unfinished order,
                    // the old one is vanished
                    String orderId = null;
                    while(rs.next()) {
                        orderId = rs.getString("order_id");
                    }

                    // if there are some info about unfinished order than orderId is no longer null.
                    if(orderId!=null) {
                        // now you can get data from orders_content
                        statement = new StringBuilder("SELECT * FROM orders_content WHERE order_id = " + orderId);
                        ps = con.prepareStatement(statement.toString());
                        rs = ps.executeQuery();
                        ArrayList<CartProduct> ordersContent = new ArrayList<>();

                        while(rs.next()) {
                            Product temp = ProductDAO.getSingleProductData(rs.getString("product_id"));
                            ordersContent.add(new CartProduct(temp, rs.getInt("quantity")));
                        }
                        // now ordersContent is filled with CartProducts
                        return ordersContent;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.getUnfinishedOrderContent() --> " + ex.getMessage());
                return null;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.getUnfinishedOrderContent() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.getUnfinishedOrderContent()");
        }
        return null;
    }
    public static ArrayList<CartProduct> getUnfinishedOrderContentOrderId(String orderId) {
        // It's needed to check if there are all information provided to execute further operations
        if (orderId != null && orderId.length()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    // now you can get data from orders_content
                    StringBuilder statement = new StringBuilder("SELECT * FROM orders_content WHERE order_id = " + orderId);
                    ps = con.prepareStatement(statement.toString());
                    ResultSet rs = ps.executeQuery();
                    ArrayList<CartProduct> ordersContent = new ArrayList<>();

                    while(rs.next()) {
                        Product temp = ProductDAO.getSingleProductData(rs.getString("product_id"));
                        ordersContent.add(new CartProduct(temp, rs.getInt("quantity")));
                    }
                    // now ordersContent is filled with CartProducts
                    return ordersContent;
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.getUnfinishedOrderContentOrderId() --> " + ex.getMessage());
                return null;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.getUnfinishedOrderContentOrderId() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.getUnfinishedOrderContentOrderId()");
        }
        return null;
    }
    public static String getUnfinishedOrderId(String user_id) {
        // It's needed to check if there are all information provided to execute further operations
        if (user_id != null && user_id.length()>0) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    // First you need to check if there are some unfinished order info in db
                    StringBuilder statement = new StringBuilder();
                    statement.append("SELECT * FROM orders WHERE user_id = ").append(user_id).append(" AND finished = 0");
                    ps = con.prepareStatement(statement.toString());
                    ResultSet rs = ps.executeQuery();

                    // There might be only one unfinished order, because every time you build second unfinished order,
                    // the old one is vanished
                    while(rs.next()) {
                        String orderId = rs.getString("order_id");
                        return orderId;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error while executing query; OrderDAO.getUnfinishedOrderId() --> " + ex.getMessage());
                return null;
            } finally {
                try {
                    if(ps != null) ps.close();
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Error while closing database connection or prepared statement; OrderDAO.getUnfinishedOrderId() --> " + ex.getMessage());
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; OrderDAO.getUnfinishedOrderId()");
        }
        return null;
    }
}
