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

        if(UserDAO.checkIfUserExists(userId)){
            User singleUser = UserDAO.getSingleUserData(userId);
            request.setAttribute("singleUser", singleUser);
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
        String birth_date = request.getParameter("birthDate");


        if(user_id == null){ request.setAttribute("msg", "Nie rozpoznano id edytowanego użytkownika. Spróbuj ponownie wyszukać użytkownika na " +
                "liście uzytkowników w menadżerze użytkowników i zedytuj jego dane jeszcze raz.");
        } else if(user_login == null){ request.setAttribute("msg", "Nie podano loginu");
        } else if (user_pass == null){ request.setAttribute("msg", "Nie podano hasła");
        } else if (first_name == null){ request.setAttribute("msg", "Nie podano imienia");
        } else if (last_name == null){ request.setAttribute("msg", "Nie podano nazwiska");
        } else if (user_email == null){ request.setAttribute("msg", "Nie podano emaila");
        } else if(!UserDAO.checkIfEmailExist(user_email, user_id)){ request.setAttribute("msg", "Podany email jest już zajęty");
        } else if(!UserDAO.checkIfLoginExist(user_login, user_id)){ request.setAttribute("msg", "Podany login jest już zajęty");
        } else {
            if(user_activation_key == null) { user_activation_key = ""; }
            boolean done = UserDAO.editGivenUser(user_id, user_login, user_pass, first_name, last_name, user_email, user_activation_key, birth_date);
            if(done){
                request.setAttribute("msg", "Pomyślnie zedytowano użytkownika");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie dodawania zedytowanych danych uzytkownika do bazy, spróbuj ponownie, albo zweryfikuj logi serwera");
            }
        }

        doGet(request, response);
    }
}
