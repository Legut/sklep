package admin;

import dao.ProductDAO;
import dao.RegisterDAO;
import dao.UserDAO;
import objects.Product;
import objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/product-manager/edit-product")
public class EditProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String msg = "";
        if(request.getAttribute("msg")!=null) {
            msg += request.getAttribute("msg");
        }

        if(ProductDAO.checkIfProductExists(productId)){
            Product singleProduct = ProductDAO.getSingleProductData(productId);
            request.setAttribute("singleProduct", singleProduct);
        }

        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/WEB-INF/admin/edit-product.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product_id = request.getParameter("product_id");
        String product_name = request.getParameter("product_name");
        String category = request.getParameter("category");
        String quantity = request.getParameter("quantity");
        String quantity_sold = request.getParameter("quantity_sold");
        String on_sale = request.getParameter("on_sale");
        String date_added = request.getParameter("date_added");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String gallery_id = request.getParameter("gallery_id");

        if(product_id == null){ request.setAttribute("msg", "Nie rozpoznano id edytowanego produktu. Spróbuj ponownie wyszukać produkt " +
                "w menadżerze produktów i zedytuj jego dane jeszcze raz.");
        } else if(product_name == null){ request.setAttribute("msg", "Nie podano nazwy produktu");
        } else if(category == null){ request.setAttribute("msg", "Nie podano kategorii produktu");
        } else if(quantity == null){ request.setAttribute("msg", "Nie podano ilości produktów na stanie");
        } else if(quantity_sold == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(on_sale == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(date_added == null){ request.setAttribute("msg", "Nie podano daty dodania produktu");
        } else if(price == null){ request.setAttribute("msg", "Nie podano ceny produktu");
        } else if(description == null){ request.setAttribute("msg", "Nie podano opisu produktu");
        } else if(gallery_id == null){ request.setAttribute("msg", "Nie podano ID galerii produktowej");
        } else {
            boolean done = ProductDAO.editGivenProduct(product_id, product_name, category, quantity, quantity_sold, on_sale, date_added, price, description, gallery_id);
            if(done){
                request.setAttribute("msg", "Pomyślnie zedytowano użytkownika");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie dodawania zedytowanych danych uzytkownika do bazy, spróbuj ponownie, albo zweryfikuj logi serwera");
            }
        }

        doGet(request, response);
    }
}
