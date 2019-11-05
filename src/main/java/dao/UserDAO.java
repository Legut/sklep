package dao;

import objects.User;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    public static long amountOfUsers() throws SQLException {
        Connection con = null;
        long amount = 0;
        PreparedStatement ps = null;
        
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS `amount` FROM users");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                amount = rs.getLong("amount");
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting users data from db; UserDAO.amountOfUsers() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                ps.close();
            }
        }
        return amount;
    }
    public static ArrayList<User> getUsersList(long startPosition, long amount) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<User> usersList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM users ORDER BY ID LIMIT ?, ?");
            ps.setLong(1, startPosition);
            ps.setLong(2, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User temp = new User(rs.getLong("ID"),
                        rs.getString("user_login"),
                        rs.getString("user_pass"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_email"),
                        rs.getString("user_registered"),
                        rs.getString("user_activation_key"),
                        rs.getString("user_role"));
                usersList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting users data from db; UserDAO.getUsersList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                ps.close();
            }
        }
        return usersList;
    }
    public static boolean checkIfUserExists(long id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM users WHERE ID = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error while checking if user exists in db; UserDAO.checkIfUserExists() -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }
    public static String deleteSingleUser(long deleteId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        if(checkIfUserExists(deleteId)) {
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("DELETE FROM users WHERE ID = ?");
                ps.setLong(1, deleteId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error while deleting user from db; UserDAO.deleteSingleUser() -->" + ex.getMessage());
                return "Wystąpił problem w trakcie usuwania użytkownika";
            } finally {
                DataConnect.close(con);
                if (ps != null) {
                    ps.close();
                }
            }
            return "Użytkownik został usunięty";
        } else {
            return null;
        }
    }
    public static boolean addSingleUser(String user_login, String user_pass, String first_name, String last_name, String user_email) throws SQLException {
        String activation_key = "";
        if (user_login != null || user_pass != null || user_email != null) {
            PreparedStatement ps = null;
            Connection con = null;

            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    ps = con.prepareStatement("INSERT INTO users(user_login, user_pass, first_name, last_name, user_email, user_registered, user_role, user_activation_key) VALUES(?,?,?,?,?,NOW(),?,?)");
                    ps.setString(1, user_login);
                    ps.setString(2, user_pass);
                    if(!first_name.isEmpty()) {
                        ps.setString(3, first_name);
                    } else {
                        ps.setString(3, "Imię");
                    }
                    if(!last_name.isEmpty()) {
                        ps.setString(4, last_name);
                    } else {
                        ps.setString(4, "Nazwisko");
                    }
                    ps.setString(5, user_email);
                    ps.setString(6, "USER");
                    ps.setString(7, activation_key);
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Error while adding user during query execution; UserDAO.addSingleUser() -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
                if (ps != null) {
                    ps.close();
                }
            }
        } else {
            return false;
        }
    }
}
