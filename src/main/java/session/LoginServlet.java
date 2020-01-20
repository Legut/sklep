package session;

import shoppingLogic.ShoppingCart;
import dao.CartsDAO;
import dao.LoginDAO;
import objects.CartProduct;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
	private String id;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.user = request.getParameter("user");
		this.pwd = request.getParameter("pwd");
		boolean valid = false;
		try {
			valid = LoginDAO.validate(user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(valid){
			ArrayList<String> userData = null;
			try {
				userData = LoginDAO.checkUserData(user, pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String user_login = userData.get(0);
			String user_email = userData.get(1);
			String user_activation_key = userData.get(2);
			String user_role = userData.get(3);
			String user_id = userData.get(4);
			HttpSession session = request.getSession();

			if(user_activation_key.isEmpty()){
				session.setAttribute("user_login", user_login);
				session.setAttribute("user_role", user_role);
				session.setAttribute("user_email", user_email);
				session.setAttribute("user_id", user_id);
				ShoppingCart shoppingCartBean = (ShoppingCart) session.getAttribute("shoppingCart");
				if (shoppingCartBean == null) {
					// there is no cart data in current session
					try {
						InitialContext ic = new InitialContext();
						shoppingCartBean = (ShoppingCart) ic.lookup("java:global/ShoppingCartImpl");

						// get cart products associated with this user from db
						Map<Long, CartProduct> cartFromDB = CartsDAO.getCartsContent(user_id);
						// save those cart products to bean instance
						shoppingCartBean.setContents(cartFromDB);
					} catch (NamingException e) {
						throw new ServletException(e);
					}
				} else {
					// there is some cart data in current session
					Map<Long, CartProduct> cartFromDB = CartsDAO.getCartsContent(user_id);
					shoppingCartBean.addAnotherCartsContent(cartFromDB);
				}

				//expiration after: 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("user_login", user_login);
				Cookie userRole = new Cookie("user_role", user_role);
				Cookie userEmail = new Cookie("user_email", user_email);
				Cookie userId = new Cookie("user_id", user_id);

				userName.setMaxAge(30*60);
				userRole.setMaxAge(30*60);
				userEmail.setMaxAge(30*60);
				userId.setMaxAge(30*60);

				response.addCookie(userName);
				response.addCookie(userRole);
				response.addCookie(userEmail);
				response.addCookie(userId);

				response.sendRedirect("/");
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
			request.setAttribute("msg", "Logowanie nie powiodło się. Podałeś złe dane.");
			doGet(request, response);
		}
	}

}
