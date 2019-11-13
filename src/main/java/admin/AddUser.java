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
        String birth_date = request.getParameter("birthDate");

        if(user_login == null){ request.setAttribute("msg", "Nie podano loginu");
        } else if (user_pass == null){ request.setAttribute("msg", "Nie podano hasła");
        } else if (first_name == null){ request.setAttribute("msg", "Nie podano imienia");
        } else if (last_name == null){ request.setAttribute("msg", "Nie podano nazwiska");
        } else if (user_email == null){ request.setAttribute("msg", "Nie podano emaila");
        } else if(!RegisterDAO.validateUserEmail(user_email)){ request.setAttribute("msg", "Podany email jest już zajęty");
        } else if(!RegisterDAO.validateUserLogin(user_login)){ request.setAttribute("msg", "Podany login jest już zajęty");
        } else {
            boolean done = RegisterDAO.addUser(user_login, user_pass, first_name, last_name, user_email, "", birth_date);
            if(done){
                request.setAttribute("msg", "Pomyślnie dodano użytkownika do bazy");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie dodawania uzytkownika do bazy, spróbuj ponownie, albo zweryfikuj logi serwera");
            }
        }

        doGet(request, response);
    }
}
