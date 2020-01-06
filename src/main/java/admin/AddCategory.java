package admin;

import dao.CategoryDAO;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/admin/category-manager/add-category")
public class AddCategory extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        request.setAttribute("currentDate", dateFormat.format(date));
        request.getRequestDispatcher("/WEB-INF/admin/add-category.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String category_name = request.getParameter("category_name");
        String category_url = request.getParameter("category_url");

        if(category_name == null){ request.setAttribute("msg", "Nie podano nazwy kategorii");
        } else if(category_url == null){ request.setAttribute("msg", "Nie podano url kategorii");
        } else {
            boolean done = CategoryDAO.addCategory(category_name, category_url);
            if(done){
                request.setAttribute("msg", "Pomyślnie dodano kategorię do bazy");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie dodawania kategorii do bazy, spróbuj ponownie, albo zweryfikuj logi serwera");
            }
        }

        doGet(request, response);
    }
}
