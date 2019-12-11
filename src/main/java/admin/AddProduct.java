package admin;

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

@WebServlet("/admin/product-manager/add-product")
public class AddProduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        request.setAttribute("currentDate", dateFormat.format(date));
        request.getRequestDispatcher("/WEB-INF/admin/add-product.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String product_name = request.getParameter("product_name");
        String category = request.getParameter("category");
        String quantity = request.getParameter("quantity");
        String quantity_sold = request.getParameter("quantity_sold");
        String price = request.getParameter("price");
        String sale_price = request.getParameter("sale_price");
        String date_added = request.getParameter("date_added");
        String description = request.getParameter("description");
        String photoLinkOne = request.getParameter("photoLinkOne");
        String photoLinkTwo = request.getParameter("photoLinkTwo");
        String photoLinkThree = request.getParameter("photoLinkThree");
        String photoLinkFour = request.getParameter("photoLinkFour");
        String featured = request.getParameter("featured");

        if(product_name == null){ request.setAttribute("msg", "Nie podano nazwy produktu");
        } else if(category == null){ request.setAttribute("msg", "Nie podano kategorii produktu");
        } else if(quantity == null){ request.setAttribute("msg", "Nie podano ilości produktów na stanie");
        } else if(quantity_sold == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(sale_price == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(date_added == null){ request.setAttribute("msg", "Nie podano daty dodania produktu");
        } else if(price == null){ request.setAttribute("msg", "Nie podano ceny produktu");
        } else if(description == null){ request.setAttribute("msg", "Nie podano opisu produktu");
        } else {
            boolean done = ProductDAO.addProduct(product_name, category, quantity, quantity_sold, sale_price, date_added, price,
                    description, photoLinkOne, photoLinkTwo, photoLinkThree, photoLinkFour, featured);
            if(done){
                request.setAttribute("msg", "Pomyślnie dodano produkt do bazy");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie dodawania produktu do bazy, spróbuj ponownie, albo zweryfikuj logi serwera");
            }
        }

        doGet(request, response);
    }
}
