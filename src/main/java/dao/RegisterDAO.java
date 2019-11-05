package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ActivationEmail;
import util.DataConnect;

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
    public static Boolean validateUserLogin(String user_login) throws SQLException {
        if (!user_login.isEmpty()) {
            PreparedStatement ps = null;
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
                return null;
            } finally {
                if (ps != null) {
                    ps.close();
                }
                DataConnect.close(con);
            }
        }
        return true;
    }
    public static Boolean validateUserEmail(String user_email) throws SQLException {
        if (!user_email.isEmpty()) {
            PreparedStatement ps = null;
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
                return null;
            } finally {
                if (ps != null) {
                    ps.close();
                }
                DataConnect.close(con);
            }
        }
        return true;
    }
    public static String addUser(String user_login, String user_pass, String first_name, String last_name, String user_email) {
        String activation_key;
        if (user_login != null || user_pass != null || user_email != null) {
            PreparedStatement ps = null;
            Connection con = null;
            activation_key = getAlphaNumericString(20);
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    String sql = "INSERT INTO users(user_login, user_pass, first_name, last_name, user_email, user_registered, user_role, user_activation_key) VALUES(?,?,?,?,?,NOW(),?,?)";
                    ps = con.prepareStatement(sql);
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
                System.out.println("Registration error when executing query; RegisterDAO.addUser() -->" + ex.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    DataConnect.close(con);
                    ActivationEmail.sendActivationEmail(activation_key, user_email);
                } catch (Exception ex) {
                    System.out.println("Registration error when closing database connection or prepared statement; RegisterDAO.addUser() -->" + ex.getMessage());
                } finally {
                    return activation_key;
                }
            }
        } else {
            return null;
        }
    }
    public static boolean checkActivationKeyAndDelete(String user_activation_key) throws SQLException {
        if (user_activation_key != null) {
            PreparedStatement ps = null;
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
                if (ps != null) {
                    ps.close();
                }
                DataConnect.close(con);
            }
        }
        return false;
    }
}