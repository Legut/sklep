package dao;

import objects.Category;
import objects.Product;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
    public static Category getCategory(long id) {
        PreparedStatement ps = null;
        Connection con = null;
        Category category = null;
        try {
            con = DataConnect.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM categories WHERE caegory_id=?";
                ps = con.prepareStatement(sql);
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    category = new Category(
                            rs.getLong("category_id"),
                            rs.getString("category_name"),
                            rs.getString("category_url"));
                }
            }
        } catch (Exception ex) {
            System.out.println("Category request error when executing query; CategoryDAO.getCategory() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getCategory() -->" + ex.getMessage()); }
        }
        return category;
    }
    public static long amountOfCategories() {
        Connection con = null;
        long amount = 0;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS `amount` FROM categories");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amount = rs.getLong("amount");
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting product data from db; ProductDAO.amountOfCategories() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.amountOfCategories() -->" + ex.getMessage()); }
        }
        return amount;
    }
    public static long amountOfCategoriesOfPattern(String pattern, int searchOption) {
        Connection con = null;
        long amount = 0;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS `amount` FROM categories WHERE category_name LIKE ?");
            if(searchOption==1){
                ps.setString(1, pattern + "%"); //zaczyna się na
            } else if(searchOption==3) {
                ps.setString(1, "%" + pattern); //kończy się na
            } else {
                ps.setString(1, "%" + pattern + "%"); //zawiera
            }
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                amount = rs.getLong("amount");
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.amountOfCategoriesOfPattern() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error while closing PreparedStatement; ProductDAO.amountOfCategoriesOfPattern() -->" + ex.getMessage());
                }
            }
        }
        return amount;
    }
    public static ArrayList<Category> getCategoriesList(long startPosition, long amount) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Category> categoriesList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM categories ORDER BY category_id LIMIT ?, ?");
            ps.setLong(1, startPosition);
            ps.setLong(2, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category temp = new Category(rs.getLong("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_url"));
                categoriesList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getCategoriesList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getCategoriesList() -->" + ex.getMessage()); }
        }
        return categoriesList;
    }
    public static ArrayList<Category> getCategoriesList() {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Category> categoriesList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM categories ORDER BY category_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category temp = new Category(rs.getLong("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_url"));
                categoriesList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getCategoriesList() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            try { ps.close(); } catch (Exception ex) { System.out.println("Product delete error when closing database connection or prepared statement; ProductDAO.getCategoriesList() -->" + ex.getMessage()); }
        }
        return categoriesList;
    }
    public static ArrayList<Category> getCategoriesListOfPattern(long startPosition, long amountPerPage, String searchByCategoryName, int searchOption) {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Category> categoriesList = new ArrayList<>();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM categories WHERE category_name LIKE ? ORDER BY category_id LIMIT ?, ?");
            if(searchOption==1){
                ps.setString(1, searchByCategoryName + "%");
            } else if(searchOption==3) {
                ps.setString(1, "%" + searchByCategoryName);
            } else {
                ps.setString(1, "%" + searchByCategoryName + "%");
            }
            ps.setLong(2, startPosition);
            ps.setLong(3, amountPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category temp = new Category(rs.getLong("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_url"));
                categoriesList.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Error while getting products data from db; ProductDAO.getCategoriesListOfPattern() -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
            if (ps != null) { try { ps.close(); } catch (SQLException ex) { System.out.println("Error while closing PreparedStatement; ProductDAO.getCategoriesListOfPattern() -->" + ex.getMessage()); } }
        }
        return categoriesList;
    }
    public static boolean deleteSingleCategory(String deleteId) {
        Connection con = null;
        PreparedStatement ps = null;
        if(checkIfCategoryExists(deleteId)) {
            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("DELETE FROM categories WHERE category_id = ?");
                ps.setString(1, deleteId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error while deleting product from db; ProductDAO.deleteSingleCategory() -->" + ex.getMessage());
                return false;
            } finally {
                DataConnect.close(con);
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Error while closing PreparedStatement; ProductDAO.deleteSingleCategory() -->" + ex.getMessage());
                    }
                }
            }
            return true;
        } else {
            System.out.println("Category with given ID doesn't exist; ProductDAO.deleteSingleCategory()");
            return false;
        }
    }
    public static boolean checkIfCategoryExists(String id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM categories WHERE category_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error while checking if category exists in db; ProductDAO.checkIfCategoryExists() -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error while closing PreparedStatement; ProductDAO.checkIfCategoryExists() -->" + ex.getMessage());
                }
            }
        }
        return false;
    }
    public static Category getSingleCategoryData(String categoryId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM categories WHERE category_id = ?");
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category singleCategory = new Category(rs.getLong("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_url"));
                return singleCategory;
            }
        } catch (SQLException ex) {
            System.out.println("Error while checking if product exists in db; ProductDAO.getSingleCategoryData() -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Error while closing PreparedStatement; ProductDAO.getSingleCategoryData() -->" + ex.getMessage());
                }
            }
        }
        return null;
    }
    public static boolean editGivenCategory(String category_id, String category_name, String category_url) {
        if (category_id != null || category_name != null || category_url != null) {
            PreparedStatement ps = null;
            Connection con = null;

            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    ps = con.prepareStatement("UPDATE categories SET category_name = ?, category_url = ? WHERE category_id = ? ");
                    ps.setString(1, category_name);
                    ps.setString(2, category_url);
                    ps.setString(3, category_id);
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Error while updating user data; ProductDAO.editGivenCategory() -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
                if (ps != null) { try { ps.close(); } catch (SQLException ex) { System.out.println("Error while closing PreparedStatement; ProductDAO.editGivenCategory() -->" + ex.getMessage()); } }
                return true;
            }
        } else {
            return false;
        }
    }
    public static boolean addCategory(String category_name, String category_url) {
        if (category_name != null || category_url != null) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = DataConnect.getConnection();
                if (con != null) {
                    ps = con.prepareStatement("INSERT INTO categories (category_name, category_url) VALUES(?,?)");
                    ps.setString(1, category_name);
                    ps.setString(2, category_url);
                    ps.executeUpdate();
                }
            } catch (Exception ex) {
                System.out.println("Registration error when executing query; ProductDAO.addCategory() -->" + ex.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    DataConnect.close(con);
                } catch (Exception ex) {
                    System.out.println("Adding product error when closing database connection or prepared statement; ProductDAO.addCategory() -->" + ex.getMessage());
                } finally {
                    return true;
                }
            }
        } else {
            System.out.println("All data must be delivered to this method; ProductDAO.addCategory() -->");
            return false;
        }
    }
}