package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DataConnect;

public class LoginDAO {

    public static boolean validate(String user, String password) {
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
            DataConnect.close(con);
        }
        return false;
    }
    public static String checkRole(String user, String password) {
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
            System.out.println("Login error; LoginDAO.checkRole() -->" + ex.getMessage());
            return "UNDEFINED";
        } finally {
            DataConnect.close(con);
        }
        return "UNDEFINED";
    }
}