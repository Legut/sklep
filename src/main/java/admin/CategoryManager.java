package admin;

import dao.CategoryDAO;
import objects.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/category-manager")
public class CategoryManager extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long page, amountPerPage, amountOfCategories;
        String deleteId, searchByCategoryName;
        int searchOption;

        if(request.getParameter("page") == null){ page = 0; } else { page = Long.parseLong(request.getParameter("page")); }
        if(request.getParameter("amountPerPage") == null){ amountPerPage = 20; } else { amountPerPage = Long.parseLong(request.getParameter("amountPerPage")); }
        if(request.getParameter("searchOption") != null) { searchOption = Integer.parseInt(request.getParameter("searchOption")); } else { searchOption = 2; }
        if(request.getParameter("deleteId") != null){ deleteId = String.valueOf(request.getParameter("deleteId"));
            if(CategoryDAO.deleteSingleCategory(deleteId)){
                request.setAttribute("msg", "Pomyślnie usunięto kategorię");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie usuwania kategorii");
            }
        }

        // Zwraca inną listę użytkowników w zależności od tego czy zostało coś wpisane w szukajkę
        if(request.getParameter("searchByCategoryName") != null){
            searchByCategoryName = request.getParameter("searchByCategoryName");
            ArrayList<Category> list = CategoryDAO.getCategoriesListOfPattern(page*amountPerPage, amountPerPage, searchByCategoryName, searchOption);

            amountOfCategories = CategoryDAO.amountOfCategoriesOfPattern(searchByCategoryName, searchOption);
            request.setAttribute("searchOption", searchOption);
            request.setAttribute("searchByCategoryName", searchByCategoryName);
            request.setAttribute("list", list);
        } else {
            ArrayList<Category> list = CategoryDAO.getCategoriesList(page*amountPerPage, amountPerPage);
            amountOfCategories = CategoryDAO.amountOfCategories();
            request.setAttribute("list", list);
        }

        // Ile stron wydrukować
        int pagesToPrint = (int)Math.ceil((double)amountOfCategories / (double)amountPerPage);

        request.setAttribute("pagesToPrint", pagesToPrint);
        request.setAttribute("currentPage", page);
        request.setAttribute("amountPerPage", amountPerPage);
        request.setAttribute("amountOfCategories", amountOfCategories);

        request.getRequestDispatcher("/WEB-INF/admin/category-manager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
