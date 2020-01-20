package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DataConnect;

public class LoginDAO {

    public static boolean validate(String user, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select user_login, user_pass from users where user_login = ? and user_pass = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error; LoginDAO.validate() -->" + ex.getMessage());
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            DataConnect.close(con);
        }
        return false;
    }
    public static String checkRole(String user, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT user_role FROM users WHERE user_login = '" + user + "' and user_pass = '" + password + "'");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("user_role");
            }
        } catch (SQLException ex) {
            System.out.println("Login error while checking role; LoginDAO.checkRole() -->" + ex.getMessage());
            return "UNDEFINED";
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                ps.close();
            }
        }
        return "UNDEFINED";
    }
    public static String checkAuth(String user, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT user_activation_key FROM users WHERE user_login = '" + user + "' and user_pass = '" + password + "'");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("user_activation_key");
            }
        } catch (SQLException ex) {
            System.out.println("Login error while checking auth key; LoginDAO.checkAuth() -->" + ex.getMessage());
            return "exception";
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                ps.close();
            }
        }
        return "exception";
    }
    public static ArrayList<String> checkUserData(String user, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<String> userData = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM users WHERE user_login = '" + user + "' and user_pass = '" + password + "'");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userData.add(rs.getString("user_login"));
                userData.add(rs.getString("user_email"));
                userData.add(rs.getString("user_activation_key"));
                userData.add(rs.getString("user_role"));
                userData.add(rs.getString("ID"));
                return userData;
            }
        } catch (SQLException ex) {
            System.out.println("Login error while checking auth key; LoginDAO.checkAuth() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }
}