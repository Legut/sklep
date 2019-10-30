package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaMySQLConnection {
    public Connection connect() {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://bcd.home.pl/01159258_sklep?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "01159258_sklep", "baza123;123");
            return conn;
        } catch (Exception e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą!");
            System.err.println(e.getMessage());
        }
        return null;
    }
    public String getAdminLogin () {
        String query = "SELECT user_login FROM users WHERE ID = 1";
        Connection conn = connect();
        ResultSet rs = null;
        String login = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                login = rs.getString("user_login");
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą! - Problem w metodzie JavaMySQLConnection.getAdminLogin()");
        }
        return login;
    }
    public String getAdminPass () {
        String query = "SELECT user_pass FROM users WHERE ID = 1";
        Connection conn = connect();
        ResultSet rs = null;
        String pass = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                pass = rs.getString("user_pass");
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą! - Problem w metodzie JavaMySQLConnection.getAdminPass()");
        }
        return pass;
    }
}