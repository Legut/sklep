package session;

import dao.LoginDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.user = request.getParameter("user");
		this.pwd = request.getParameter("pwd");
		boolean valid = LoginDAO.validate(user, pwd);

		if(valid){
			ArrayList<String> userData = LoginDAO.checkUserData(user, pwd);
			String user_login = userData.get(0);
			String user_email = userData.get(1);
			String user_activation_key = userData.get(2);
			String user_role = userData.get(3);
			HttpSession session = request.getSession();

			if(user_activation_key.isEmpty()){
				session.setAttribute("user_login", user_login);
				session.setAttribute("user_role", user_role);
				session.setAttribute("user_email", user_email);

				//expiration after: 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("user_login", user_login);
				Cookie userRole = new Cookie("user_role", user_role);
				Cookie userEmail = new Cookie("user_email", user_email);

				userName.setMaxAge(30*60);
				userRole.setMaxAge(30*60);
				userEmail.setMaxAge(30*60);

				response.addCookie(userName);
				response.addCookie(userRole);
				response.addCookie(userEmail);

				response.sendRedirect("/login-success");
			} else {
				session.setAttribute("user_activation_key", user_activation_key);
				session.setAttribute("user_email", user_email);

				//expiration after: 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userActivationKey = new Cookie("user_activation_key", user_activation_key);
				Cookie userEmail = new Cookie("user_email", user_email);

				userActivationKey.setMaxAge(30*60);
				userEmail.setMaxAge(30*60);

				response.addCookie(userActivationKey);
				response.addCookie(userEmail);

				response.sendRedirect("/account-not-verified");
			}
		} else {
			response.sendRedirect("/login-failed");
		}
	}

}
