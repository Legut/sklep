package admin;

import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/product-manager/add-product")
public class AddProduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/admin/add-product.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product_name = request.getParameter("product_name");
        String category = request.getParameter("category");
        String quantity = request.getParameter("quantity");
        String quantity_sold = request.getParameter("quantity_sold");
        String sale_price = request.getParameter("sale_price");
        String date_added = request.getParameter("date_added");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String gallery_id = request.getParameter("gallery_id");
        String photoLinkOne = request.getParameter("photoLinkOne");

        if(product_name == null){ request.setAttribute("msg", "Nie podano nazwy produktu");
        } else if(category == null){ request.setAttribute("msg", "Nie podano kategorii produktu");
        } else if(quantity == null){ request.setAttribute("msg", "Nie podano ilości produktów na stanie");
        } else if(quantity_sold == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(sale_price == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(date_added == null){ request.setAttribute("msg", "Nie podano daty dodania produktu");
        } else if(price == null){ request.setAttribute("msg", "Nie podano ceny produktu");
        } else if(description == null){ request.setAttribute("msg", "Nie podano opisu produktu");
        } else if(gallery_id == null){ request.setAttribute("msg", "Nie podano ID galerii produktowej");
        } else {
            boolean done = ProductDAO.addProduct(product_name, category, quantity, quantity_sold, sale_price, price, description, gallery_id);
            if(done){
                request.setAttribute("msg", "Pomyślnie dodano produkt do bazy");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie dodawania produktu do bazy, spróbuj ponownie, albo zweryfikuj logi serwera");
            }
        }

        doGet(request, response);
    }
}
