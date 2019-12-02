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
        long page, amountPerPage, amountOfProducts;
        String deleteId, searchByProductName;
        int searchOption;

        if(request.getParameter("page") == null){ page = 0; } else { page = Long.parseLong(request.getParameter("page")); }
        if(request.getParameter("amountPerPage") == null){ amountPerPage = 20; } else { amountPerPage = Long.parseLong(request.getParameter("amountPerPage")); }
        if(request.getParameter("searchOption") != null) { searchOption = Integer.parseInt(request.getParameter("searchOption")); } else { searchOption = 2; }
        if(request.getParameter("deleteId") != null){ deleteId = String.valueOf(request.getParameter("deleteId"));
            if(ProductDAO.deleteSingleProduct(deleteId)){
                request.setAttribute("msg", "Pomyślnie usunięto produkt");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie usuwania produktu");
            }
        }

        // Zwraca inną listę użytkowników w zależności od tego czy zostało coś wpisane w szukajkę
        if(request.getParameter("searchByProductName") != null){
            searchByProductName = request.getParameter("searchByProductName");
            ArrayList<Product> list = ProductDAO.getProductListOfPattern(page*amountPerPage, amountPerPage, searchByProductName, searchOption);

            amountOfProducts = ProductDAO.amountOfProductsOfPattern(searchByProductName, searchOption);
            request.setAttribute("searchOption", searchOption);
            request.setAttribute("searchByProductName", searchByProductName);
            request.setAttribute("list", list);
        } else {
            ArrayList<Product> list = ProductDAO.getProductsList(page*amountPerPage, amountPerPage);
            amountOfProducts = ProductDAO.amountOfProducts();
            request.setAttribute("list", list);
        }

        // Ile stron wydrukować
        int pagesToPrint = (int)Math.ceil((double)amountOfProducts / (double)amountPerPage);

        request.setAttribute("pagesToPrint", pagesToPrint);
        request.setAttribute("currentPage", page);
        request.setAttribute("amountPerPage", amountPerPage);
        request.setAttribute("amountOfUsers", amountOfProducts);

        request.getRequestDispatcher("/WEB-INF/admin/product-manager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
