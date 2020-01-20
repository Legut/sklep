package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.CategoryDAO;
import dao.ProductDAO;
import objects.Category;
import objects.Product;

import javax.json.Json;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/tester")
public class ProductsData extends HttpServlet {
    private JsonArray list;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
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

        // get data from DB using created statement
        ArrayList<Product> productsList = ProductDAO.getProductsListCustomStatement(statement);

        // create object for additional values other than products
        JsonObject attributesList = new JsonObject();
        attributesList.addProperty("amountOfPages", 10);

        // create GsonBuilder, which is used later for parsing data to string.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // parse list of products and list of attributes to two separate JsonElements
        JsonElement listElement = gson.toJsonTree(productsList);
        JsonElement additionalElement = gson.toJsonTree(attributesList);

        // Create the json object, which is going to be the root of the tree
        JsonObject data = new JsonObject();

        // add two separate elements to the root object to build a final tree
        data.add("list", listElement);
        data.add("additional", additionalElement);

        // parse the final tree to string
        String jsonStr = gson.toJson(data);

        // push out the data
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonStr);
        response.getWriter().flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isInStock = false;
        double minPrice = 0;
        double maxPrice = 0;
        long page = 1;
        long perPage = 9;
        int listOrder = 1;
        String chosenCategory = null;

        if(request.getParameter("minPrice")!=null) { minPrice = Double.parseDouble(request.getParameter("minPrice")); }
        if(request.getParameter("maxPrice")!=null) { maxPrice = Double.parseDouble(request.getParameter("maxPrice")); }
        if(request.getParameter("listOrder")!=null) { listOrder = Integer.parseInt(request.getParameter("listOrder")); }
        if(request.getParameter("chosenCategory")!=null) { chosenCategory = request.getParameter("chosenCategory"); }
        if(request.getParameter("isInStock")!=null) { isInStock = Boolean.parseBoolean(request.getParameter("isInStock")); }
        if(request.getParameter("page")!=null) { page = Long.parseLong(request.getParameter("page")); }
        if(request.getParameter("perPage")!=null) { perPage = Long.parseLong(request.getParameter("perPage")); }
        String searchByProductName = request.getParameter("searchByProductName");
        boolean isWhereInStatement = false;

        String statement = "";
        ArrayList<Category> categoryList = CategoryDAO.getCategoriesList();

        if(chosenCategory!=null) {
            for (Category category : categoryList) {
                if (chosenCategory.equals(category.getCategoryURL())) {
                    request.setAttribute("categoryURL", category.getCategoryURL());
                    request.setAttribute("categoryName", category.getCategoryName());
                    statement += " WHERE P.category_id = " + category.getId();
                    isWhereInStatement = true;
                }
            }
        } else {
            // jestem na sklepie, w sensie ze nie ma wybranej kategorii
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

        switch(listOrder){
            case 1:
                statement += " ORDER BY P.product_name ASC";
                break;
            case 2:
                statement += " ORDER BY P.product_name DESC";
                break;
            case 3:
                statement += " ORDER BY P.date_added DESC";
                break;
            case 4:
                statement += " ORDER BY P.date_added ASC";
                break;
            case 5:
                statement += " ORDER BY P.price ASC";
                break;
            case 6:
                statement += " ORDER BY P.price DESC";
                break;
        }

        statement += " LIMIT " + ((page-1)*perPage) + ", " + perPage;

        // get data from DB using created statement
        ArrayList<Product> productsList = ProductDAO.getProductsListCustomStatement("SELECT * FROM products AS P LEFT JOIN categories AS C ON P.category_id = C.category_id" + statement);
        long amountOfProducts = ProductDAO.getAmountOfProductsCustomStatement("SELECT COUNT(*) AS 'amountOfPages' FROM products AS P LEFT JOIN categories AS C ON P.category_id = C.category_id" + statement);

        // create object for additional values other than products
        JsonObject attributesList = new JsonObject();
        attributesList.addProperty("amountOfPages", amountOfProducts);

        // create GsonBuilder, which is used later for parsing data to string.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // parse list of products and list of attributes to two separate JsonElements
        JsonElement listElement = gson.toJsonTree(productsList);
        JsonElement additionalElement = gson.toJsonTree(attributesList);

        // Create the json object, which is going to be the root of the tree
        JsonObject data = new JsonObject();

        // add two separate elements to the root object to build a final tree
        data.add("list", listElement);
        data.add("additional", additionalElement);

        // parse the final tree to string
        String jsonStr = gson.toJson(data);

        // push out the data
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonStr);
        response.getWriter().flush();
    }
}
