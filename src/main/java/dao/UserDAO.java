package dao;

import objects.User;
import util.DataConnect;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                        rs.getString("user_role"),
                        rs.getString("birth_date"));
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
    public static boolean checkIfUserExists(String id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM users WHERE ID = ?");
            ps.setString(1, id);
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
    public static String deleteSingleUser(String deleteId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        if(checkIfUserExists(deleteId)) {
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("DELETE FROM users WHERE ID = ?");
                ps.setString(1, deleteId);
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
    public static boolean checkIfLoginExist(String user_login, String userId) {
        if (!user_login.isEmpty()) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("SELECT * FROM users WHERE user_login = ? AND ID != ?");
                ps.setString(1, user_login);
                ps.setString(2, userId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while trying to check in database if given email is valid; RegisterDAO.validateUserLogin() -->" + ex.getMessage());
                return false;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Error while trying to close PreparedStatement; RegisterDAO.validateUserLogin() -->" + ex.getMessage());
                    }
                }
                DataConnect.close(con);
            }
        }
        return true;
    }
    public static boolean checkIfEmailExist(String user_email, String userId) {
        if (!user_email.isEmpty()) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("SELECT * FROM users WHERE user_email = ? AND ID != ?");
                ps.setString(1, user_email);
                ps.setString(2, userId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while checking in db if email is valid; RegisterDAO.validateUserEmail() -->" + ex.getMessage());
                return false;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Error while closing PreparedStatement; RegisterDAO.validateUserEmail() -->" + ex.getMessage());
                    }
                }
                DataConnect.close(con);
            }
        }
        return true;
    }
    public static User getSingleUserData(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM users WHERE ID = ?");
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User singleUser = new User(rs.getLong("ID"),
                        rs.getString("user_login"),
                        rs.getString("user_pass"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_email"),
                        rs.getString("user_registered"),
                        rs.getString("user_activation_key"),
                        rs.getString("user_role"),
                        rs.getString("birth_date"));
                return singleUser;
            }
        } catch (SQLException ex) {
            System.out.println("Error while checking if user exists in db; UserDAO.checkIfUserExists() -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }
    public static boolean editGivenUser(String userId, String user_login, String user_pass, String first_name, String last_name, String user_email, String activation_key, String birth_date) {
        if (user_login != null || user_pass != null || user_email != null) {
            PreparedStatement ps = null;
            Connection con = null;

            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    ps = con.prepareStatement("UPDATE users SET user_login = ?, user_pass = ?, first_name = ?, last_name = ?, user_email = ?, user_role = ?, user_activation_key = ?, birth_date = ? WHERE ID = ? ");
                    ps.setString(1, user_login);
                    ps.setString(2, user_pass);
                    ps.setString(3, first_name);
                    ps.setString(4, last_name);
                    ps.setString(5, user_email);
                    ps.setString(6, "USER");
                    ps.setString(7, activation_key);
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    ps.setString(8, birth_date);
                    ps.setString(9, userId);
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Error while updating user data; UserDAO.editGivenUser() -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
                if (ps != null) { try { ps.close(); } catch (SQLException ex) { System.out.println("Error while closing PreparedStatement; UserDAO.editGivenUser() -->" + ex.getMessage()); } }
                return true;
            }
        } else {
            return false;
        }
    }
}
