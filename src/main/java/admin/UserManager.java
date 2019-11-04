package admin;

import dao.UserDAO;
import objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/user-manager")
public class UserManager extends HttpServlet {
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

        long amountOfUsers = UserDAO.amountOfUsers();
        pagesToPrint = (int)Math.ceil(amountOfUsers / amountPerPage);
        ArrayList<User> list = UserDAO.getUsersList(page*amountPerPage, amountPerPage);

        request.setAttribute("pagesToPrint", pagesToPrint);
        request.setAttribute("currentPage", page);
        request.setAttribute("amountPerPage", amountPerPage);
        request.setAttribute("amountOfUsers", amountOfUsers);
        request.setAttribute("list", list);

        request.getRequestDispatcher("/WEB-INF/admin/user-manager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
