package login;

import dao.RegisterDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user-activation")
public class UserActivation  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("key") != null){
            String user_activation_key = request.getParameter("key");
            boolean valid = false;
            try {
                valid = RegisterDAO.checkActivationKeyAndDelete(user_activation_key);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(valid){
                request.setAttribute("activation", true);
            } else {
                request.setAttribute("activation", false);
            }
            request.getRequestDispatcher("/WEB-INF/pages/userActivation.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
