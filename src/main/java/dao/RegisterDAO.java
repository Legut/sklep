package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
                    sendActivationEmail(activation_key, user_email);
                } catch (Exception ex) {
                    System.out.println("Registration error when closing database connection or prepared statement; RegisterDAO.addUser() -->" + ex.getMessage());
                }
            }
        } else {
            return "failed";
        }
        return null;
    }
    private static void sendActivationEmail(String activation_key, String user_email) {
        final String username = "monopolowy24h@bcd.pl";
        final String password = "1dX3;21dfs3412davD!";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "bcd.home.pl");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("monopolowy24h@bcd.pl"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(user_email)
            );
            //TODO: zastąpić sztywny link aktywacyjny takim co nie jest sztywny xd
            message.setSubject("Rejestracja w Monopolowy24h - Aktywacja konta");
            message.setText("Witaj! " +
                    "Jeśli otrzymałeś ten email, to znaczy że proces rejestracji przebiegł pomyślnie. " +
                    "Aby korzystać z utworzonego konta należy je aktywować, klikając w poniższy link." +
                    "\nhttp://localhost:8080/user-activation?key=" + activation_key +
                    "\n Jeśli nie rejestrowałeś się w sklepie Monopolowy24h, to zignoruj tę wiadomość." +
                    "\n\n---------------------------------------" +
                    "\nWiadomość wygenerowana automatycznie. Prosimy nie odpowiadać na tę wiadomość.");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException ex) {
            System.out.println("Sending activation email error; RegisterDAO.sendActivationEmail() -->" + ex.getMessage());
        }
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