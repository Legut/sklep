package pages;

import dao.CategoryDAO;
import dao.ProductDAO;
import objects.Category;
import objects.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/sklep")
public class Sklep extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String path = request.getPathInfo();
        boolean isInStock = false;
        double minPrice = 0;
        double maxPrice = 0;
        long page = 1;
        long perPage = 9;
        if(request.getParameter("minPrice")!=null) { minPrice = Double.parseDouble(request.getParameter("minPrice")); }
        if(request.getParameter("maxPrice")!=null) { maxPrice = Double.parseDouble(request.getParameter("maxPrice")); }
        if(request.getParameter("isInStock")!=null) { isInStock = Boolean.parseBoolean(request.getParameter("isInStock")); }
        if(request.getParameter("page")!=null) { page = Long.parseLong(request.getParameter("page")); }
        if(request.getParameter("perPage")!=null) { perPage = Long.parseLong(request.getParameter("perPage")); }
        String searchByProductName = request.getParameter("searchByProductName");
        boolean isWhereInStatement = false;

        String statement = "SELECT * FROM products AS P LEFT JOIN categories AS C ON P.category_id = C.category_id";
        ArrayList<Category> categoryList = CategoryDAO.getCategoriesList();

        for(Category category: categoryList) {
            if(path!=null) {
                if (path.equals("/" + category.getCategoryURL())) {
                    request.setAttribute("categoryURL", category.getCategoryURL());
                    request.setAttribute("categoryName", category.getCategoryName());
                    statement += " WHERE P.category_id = " + category.getId();
                    isWhereInStatement = true;
                }
            } else {
                // jestem na sklepie
            }
        }
        if(isInStock){
            if(!isWhereInStatement){
                statement += " WHERE";
                isWhereInStatement = true;
            }
            statement += " quantity > 0";
            request.setAttribute("isInStock", "true");
        }
        if(minPrice>0){
            if(!isWhereInStatement){
                statement += " WHERE";
                isWhereInStatement = true;
            } else {
                statement += " AND";
            }
            statement += " (price > " + minPrice + " OR (sale_price > " + minPrice + " AND sale_price != 0))";
            request.setAttribute("minPrice", minPrice);
        }
        if(maxPrice>0){
            if(!isWhereInStatement){
                statement += " WHERE";
                isWhereInStatement = true;
            } else {
                statement += " AND";
            }
            statement += " (price < " + maxPrice + " OR (sale_price < " + maxPrice + " AND sale_price != 0))";
            request.setAttribute("maxPrice", maxPrice);
        }
        if(searchByProductName!=null){
            if(!searchByProductName.equals("")) {
                if (!isWhereInStatement) {
                    statement += " WHERE";
                    isWhereInStatement = true;
                } else {
                    statement += " AND";
                }
                statement += " product_name LIKE '%" + searchByProductName + "%'";
                request.setAttribute("searchByProductName", searchByProductName);
            }
        }

        statement += " ORDER BY P.product_id";
        statement += " LIMIT " + ((page-1)*perPage) + ", " + perPage;
        System.out.println(statement);
        ArrayList<Product> productsList = ProductDAO.getProductsListCustomStatement(statement);
        ArrayList<Product> productsList = ProductDAO.getProductsList(0,9);
        request.setAttribute("productsList", productsList);*/
        request.getRequestDispatcher("/WEB-INF/pages/sklep.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}