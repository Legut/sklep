package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import util.ActivationEmail;
import util.DataConnect;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegisterDAO {
    private static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
    public static boolean validateUserLogin(String user_login) {
        if (user_login != null) {
            PreparedStatement ps;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("Select user_login from users where user_login = ?");
                ps.setString(1, user_login);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return false;
                }
            } catch (SQLException ex) {
                System.out.println("Registration error; RegisterDAO.validateUserLogin() -->" + ex.getMessage());
                return false;
            } finally {
                DataConnect.close(con);
            }
        }
        return true;
    }
    public static boolean validateUserEmail(String user_email) {
        if (user_email != null) {
            PreparedStatement ps;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("Select user_email from users where user_email = ?");
                ps.setString(1, user_email);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return false;
                }
            } catch (SQLException ex) {
                System.out.println("Registration error; RegisterDAO.validateUserEmail() -->" + ex.getMessage());
                return false;
            } finally {
                DataConnect.close(con);
            }
        }
        return true;
    }
    public static String addUser(String user_login, String user_pass, String first_name, String last_name, String user_email) {
        if (user_login != null || user_pass != null || user_email != null) {
            PreparedStatement ps = null;
            Connection con = null;
            String activation_key = getAlphaNumericString(20);
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    String sql = "INSERT INTO users(user_login, user_pass, first_name, last_name, user_email, user_registered, user_role, user_activation_key) VALUES(?,?,?,?,?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, user_login);
                    ps.setString(2, user_pass);
                    ps.setString(3, first_name);
                    ps.setString(4, last_name);
                    ps.setString(5, user_email);
                    ps.setString(6, "NOW()");
                    ps.setString(7, "USER");
                    ps.setString(8, activation_key);
                    ps.executeQuery();
                    System.out.println("Data Added Successfully");
                }
            } catch (Exception ex) {
                System.out.println("Registration error when executing query; RegisterDAO.addUser() -->" + ex.getMessage());
            } finally {
                try {
                    con.close();
                    ps.close();
                    ActivationEmail.sendActivationEmail(activation_key, user_email);
                } catch (Exception ex) {
                    System.out.println("Registration error when closing database connection or prepared statement; RegisterDAO.addUser() -->" + ex.getMessage());
                }
            }
        } else {
            return "failed";
        }
        return null;
    }
    public static boolean checkActivationKeyAndDelete(String user_activation_key) {
        if (user_activation_key != null) {
            PreparedStatement ps;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("Select user_activation_key from users where user_activation_key = ?");
                ps.setString(1, user_activation_key);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    ps = con.prepareStatement("UPDATE `users` SET `user_activation_key` = ? WHERE `user_activation_key` = ?");
                    ps.setString(1, "");
                    ps.setString(2, user_activation_key);

                    int test = ps.executeUpdate();
                    if(test>0) {
                        return true;
                    } else {
                        System.out.println("Error updating database; RegisterDAO.checkActivationKeyAndDelete()");
                        return false;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("User activation error; RegisterDAO.checkActivationKeyAndDelete() -->" + ex.getMessage());
                return false;
            } finally {
                DataConnect.close(con);
            }
        }
        return false;
    }
}