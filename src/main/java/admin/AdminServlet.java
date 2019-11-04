package admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user_role")!=null){
            String rola = session.getAttribute("user_role").toString();
            if(rola.equals("ADMIN")){
                request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/admin/no-access.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/admin/no-access.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
