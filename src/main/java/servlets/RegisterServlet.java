package servlets;

import dao.RegisterDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private String user_login;
    private String user_pass;
    private String first_name;
    private String last_name;
    private String user_email;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.user_login = request.getParameter("userlogin");
        this.user_pass = request.getParameter("pwd");
        this.first_name = request.getParameter("fistname");
        this.last_name = request.getParameter("lastname");
        this.user_email = request.getParameter("email");

        response.setContentType("text/html;charset=UTF-8");
        boolean valid_login = RegisterDAO.validateUserLogin(user_login);
        boolean valid_email = RegisterDAO.validateUserEmail(user_email);
        if(valid_login){
            if(valid_email) {
                RegisterDAO.addUser(user_login, user_pass, first_name, last_name, user_email);
                response.sendRedirect("/register-success");
            } else {
                response.sendRedirect("/register-failed");
                //TODO: info, że nie udało się bo na dany email jest już jedno konto
            }
        } else {
            response.sendRedirect("/register-failed");
            // TODO: info, że nie udało się bo login jest już zajęty
        }
    }
}
