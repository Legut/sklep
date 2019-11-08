package admin;

import dao.RegisterDAO;
import dao.UserDAO;
import objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/user-manager/edit-user")
public class EditUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String msg = "";
        if(request.getAttribute("msg")!=null) {
            msg += request.getAttribute("msg");
        }
        boolean userExist = false;

        try {
            userExist = UserDAO.checkIfUserExists(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(userExist){
            try {
                User singleUser = UserDAO.getSingleUserData(userId);
                request.setAttribute("singleUser", singleUser);
            } catch (SQLException e) {
                msg += "\nProblem w trakcie pobierania danych użytkownika z bazy; Error: " + e;
            }
        }

        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/WEB-INF/admin/edit-user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("userId");
        String user_login = request.getParameter("userlogin");
        String user_pass = request.getParameter("pwd");
        String first_name = request.getParameter("firstname");
        String last_name = request.getParameter("lastname");
        String user_email = request.getParameter("email");
        String user_activation_key = request.getParameter("userActivationKey");
        String msg = "";
        Boolean validEmail = null, validLogin = null;

        if(user_login == null){ msg = "Nie podano loginu";
        } else if (user_pass == null){ msg = "Nie podano hasła";
        } else if (first_name == null){ msg = "Nie podano imienia";
        } else if (last_name == null){ msg = "Nie podano nazwiska";
        } else if (user_email == null){ msg = "Nie podano emaila";
        } else {
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
            msg += "\nWystąpił problem w trakcie sprawdzenia poprawności danych";
        } else {
            if(validEmail){
                if(validLogin){
                    boolean done = false;
                    try {
                        done = UserDAO.editGivenUser(user_id, user_login, user_pass, first_name, last_name, user_email, user_activation_key);
                    } catch (SQLException e) {
                        msg += "\nNie udało się dodać użytkownika; Error: " + e;
                    }
                    if(done){
                        msg += "\nPomyślnie zedytowano użytkownika";
                    }
                } else { msg += "\nPodany login jest już zajęty"; }
            } else { msg += "\nPodany email jest już zajęty"; }
        }

        request.setAttribute("msg", msg);
        doGet(request, response);
    }
}
