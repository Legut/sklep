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
        }
        return usersList;
    }

    public static boolean checkIfUserExists(long id) {
        Connection con = null;
        PreparedStatement ps;

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
        }
        return false;
    }

    public static String deleteSingleUser(long deleteId) {
        Connection con = null;
        PreparedStatement ps;
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
            }
            return "Użytkownik został usunięty";
        } else {
            return null;
        }
    }
}
