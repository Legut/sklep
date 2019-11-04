package dao;

import objects.User;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    public static ArrayList<User> getUsersList(int startPosition, int amount) {
        Connection con = null;
        PreparedStatement ps;
        ArrayList<User> usersList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM users ORDER BY id LIMIT ?, ?");
            ps.setString(1, String.valueOf(startPosition));
            ps.setString(1, String.valueOf(amount));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User temp = new User(rs.getInt("ID"),
                        rs.getString("user_login"),
                        rs.getString("user_pass"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_email"),
                        rs.getString("user_registred"),
                        rs.getString("user_activation_key"),
                        rs.getString("user_role"));
                usersList.add(temp);
                return usersList;
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting users data from db; UserDAO.getUsersList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return null;
    }
}
