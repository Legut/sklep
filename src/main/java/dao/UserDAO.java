package dao;

import objects.User;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    public static long amountOfUsers() {
        Connection con = null;
        long amount = 0;
        PreparedStatement ps;
        
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
        }
        return amount;
    }
    public static ArrayList<User> getUsersList(long startPosition, long amount) {
        Connection con = null;
        PreparedStatement ps;
        ArrayList<User> usersList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM users ORDER BY id LIMIT ?, ?");
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
        }
        return usersList;
    }
}
