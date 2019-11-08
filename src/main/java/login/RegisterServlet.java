package login;

import dao.RegisterDAO;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_login = request.getParameter("userlogin");
        String user_pass = request.getParameter("pwd");
        String first_name = request.getParameter("firstname");
        String last_name = request.getParameter("lastname");
        String user_email = request.getParameter("email");

        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(user_email)) {
            Boolean valid_login = null;
            try {
                valid_login = RegisterDAO.validateUserLogin(user_login);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Boolean valid_email = null;
            try {
                valid_email = RegisterDAO.validateUserEmail(user_email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(valid_login==null || valid_email==null) {
                request.setAttribute("message", "Wystąpił problem w trakcie rejestracji. Skontaktuj się z administratorem.");
                doGet(request, response);
            } else {
                if(valid_login){
                    if(valid_email) {
                        String activation_key = RegisterDAO.addUser(user_login, user_pass, first_name, last_name, user_email);
                        if(activation_key!=null) {
                            response.sendRedirect("/register-success");
                        } else {
                            request.setAttribute("message", "Coś poszło nie tak w trakcie dodawania użytkownika do bazy. Spróbuj ponownie lub skontaktuj się z administratoracją sklepu.");
                            doGet(request, response);
                        }
                    } else {
                        request.setAttribute("message", "Istnieje już uzytkownik o podanym emailu");
                        doGet(request, response);
                    }
                } else {
                    request.setAttribute("message", "Podana nazwa użytkownika jest zajęta");
                    doGet(request, response);
                }
            }
        } else {
            request.setAttribute("message", "Podany email jest błędny.");
            doGet(request, response);
        }
    }
}
