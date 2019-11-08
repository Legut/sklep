package admin;

import dao.RegisterDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/user-manager/add-user")
public class AddUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/admin/add-user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_login = request.getParameter("userlogin");
        String user_pass = request.getParameter("pwd");
        String first_name = request.getParameter("firstname");
        String last_name = request.getParameter("lastname");
        String user_email = request.getParameter("email");
        String msg;
        Boolean validEmail = null, validLogin = null;

        if(user_login == null){
            msg = "\nNie podano loginu";
        } else if (user_pass == null){
            msg = "\nNie podano hasła";
        } else if (first_name == null){
            msg = "\nNie podano imienia";
        } else if (last_name == null){
            msg = "\nNie podano nazwiska";
        } else if (user_email == null){
            msg = "\nNie podano emaila";
        } else {
            msg = "\nPoprawnie przyjęto wszystkie dane";
            try {
                validLogin = RegisterDAO.validateUserLogin(user_login);
            } catch (SQLException e) {
                msg += "\nProblem w trakcie sprawdzania czy użytkownik o podanym loginie już istnieje; Error: " + e;
            }
            try {
                validEmail = RegisterDAO.validateUserEmail(user_email);
            } catch (SQLException e) {
                msg += "\nProblem w trakcie sprawdzania czy użytkownik o podanym mailu już istnieje; Error: " + e;
            }
        }

        if(validEmail == null || validLogin == null) {
            msg += "\nWystąpił problem w trakcie rejestracji.";
        } else {
            if(validEmail){
                if(validLogin){
                    boolean done = false;
                    try {
                        done = UserDAO.addSingleUser(user_login, user_pass, first_name, last_name, user_email);
                    } catch (SQLException e) {
                        msg += "\nNie udało się dodać użytkownika; Error: " + e;
                    }
                    if(done){
                        msg += "\nPomyślnie dodano użytkownika do bazy";
                    }
                } else {
                    msg += "\nPodany login jest już zajęty";
                }
            } else {
                msg += "\nPodany email jest już zajęty";
            }
        }

        request.setAttribute("msg", msg);
        doGet(request, response);
    }
}
