package session;

import dao.LoginDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pwd;
	private String user;
	private String role;
	private boolean auth;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request parameters for userID and password
		this.user = request.getParameter("user");
		this.pwd = request.getParameter("pwd");
		response.setContentType("text/html;charset=UTF-8");

		boolean valid = LoginDAO.validate(user, pwd);
		if(valid){
			//TODO: sprawdzanie czy użytkownik jest aktywowany (Jeśli istnieje klucz w polu z
			// kluczem przy danym uzytkowniku to nieaktywny, jeśli puste pole to aktywny)
			this.role = LoginDAO.checkRole(user, pwd);
			this.auth = LoginDAO.checkAuth(user, pwd);
			if(auth){
				HttpSession session = request.getSession();
				session.setAttribute("username", user);
				session.setAttribute("role", role);
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("user", user);
				Cookie userRole = new Cookie("role", role);
				userName.setMaxAge(30*60);
				response.addCookie(userName);
				response.addCookie(userRole);
				response.sendRedirect("/login-success");
			} else {
				response.sendRedirect("/account-not-verified");
			}
		} else {
			response.sendRedirect("/login-failed");
		}
	}

}
