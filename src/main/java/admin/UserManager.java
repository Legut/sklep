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
        long page, amountPerPage, deleteId;
        String msg = null;
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

        if(request.getParameter("deleteId") != null){
            deleteId = Long.valueOf(request.getParameter("deleteId"));
            try {
                msg = UserDAO.deleteSingleUser(deleteId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long amountOfUsers = 0;
        try {
            amountOfUsers = UserDAO.amountOfUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pagesToPrint = (int)Math.ceil((double)amountOfUsers / (double)amountPerPage);
        ArrayList<User> list = null;
        try {
            list = UserDAO.getUsersList(page*amountPerPage, amountPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("pagesToPrint", pagesToPrint);
        request.setAttribute("currentPage", page);
        request.setAttribute("amountPerPage", amountPerPage);
        request.setAttribute("amountOfUsers", amountOfUsers);
        request.setAttribute("list", list);
        request.setAttribute("msg", msg);

        request.getRequestDispatcher("/WEB-INF/admin/user-manager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
