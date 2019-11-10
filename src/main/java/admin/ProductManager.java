package admin;

import dao.ProductDAO;
import objects.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/product-manager")
public class ProductManager extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long page, amountPerPage;
        int pagesToPrint;

        if(request.getParameter("page") == null){
            page = 0;
        } else {
            page = Long.valueOf(request.getParameter("page"));
        }

        if(request.getParameter("amountPerPage") == null){
            amountPerPage = 20;
        } else {
            amountPerPage = Long.valueOf(request.getParameter("amountPerPage"));
        }

        long amountOfProducts = ProductDAO.amountOfProducts();
        pagesToPrint = (int)Math.ceil(amountOfProducts / amountPerPage);
        ArrayList<Product> list = ProductDAO.getProductsList(page*amountPerPage, amountPerPage);

        request.setAttribute("pagesToPrint", pagesToPrint);
        request.setAttribute("currentPage", page);
        request.setAttribute("amountPerPage", amountPerPage);
        request.setAttribute("amountOfUsers", amountOfProducts);
        request.setAttribute("list", list);

        request.getRequestDispatcher("/WEB-INF/admin/product-manager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
