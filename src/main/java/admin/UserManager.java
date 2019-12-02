package admin;

import dao.UserDAO;
import objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/admin/user-manager")
public class UserManager extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long page, amountPerPage, amountOfUsers;
        String deleteId, searchByUserName;
        int searchOption;

        // Ustawianie wartości domyślnych i reagowanie na pojawienie się zmiennych
        if(request.getParameter("page") == null){ page = 0; } else { page = Long.parseLong(request.getParameter("page")); }
        if(request.getParameter("amountPerPage") == null){ amountPerPage = 20; } else { amountPerPage = Long.parseLong(request.getParameter("amountPerPage")); }
        if(request.getParameter("searchOption") != null) { searchOption = Integer.parseInt(request.getParameter("searchOption")); } else { searchOption = 2; }
        if(request.getParameter("deleteId") != null){ deleteId = String.valueOf(request.getParameter("deleteId"));
            if(UserDAO.deleteSingleUser(deleteId)){
                request.setAttribute("msg", "Pomyślnie usunięto użytkownika");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie usuwania użytkownika");
            }
        }

        // Zwraca inną listę użytkowników w zależności od tego czy zostało coś wpisane w szukajkę
        if(request.getParameter("searchByUserName") != null){
            searchByUserName = request.getParameter("searchByUserName");
            ArrayList<User> list = UserDAO.getUsersListOfPattern(page*amountPerPage, amountPerPage, searchByUserName, searchOption);

            amountOfUsers = UserDAO.amountOfUsersOfPattern(searchByUserName, searchOption);
            request.setAttribute("searchOption", searchOption);
            request.setAttribute("searchByUserName", searchByUserName);
            request.setAttribute("list", list);
        } else {
            ArrayList<User> list = UserDAO.getUsersList(page*amountPerPage, amountPerPage);
            amountOfUsers = UserDAO.amountOfUsers();
            request.setAttribute("list", list);
        }
        
        // Ile stron wydrukować
        int pagesToPrint = (int)Math.ceil((double)amountOfUsers / (double)amountPerPage);

        request.setAttribute("pagesToPrint", pagesToPrint);
        request.setAttribute("currentPage", page);
        request.setAttribute("amountPerPage", amountPerPage);
        request.setAttribute("amountOfUsers", amountOfUsers);

        request.getRequestDispatcher("/WEB-INF/admin/user-manager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
