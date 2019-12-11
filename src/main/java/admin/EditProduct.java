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
        request.setCharacterEncoding("UTF-8");
        String productId;
        if(request.getParameter("productId")!=null) {
            productId = request.getParameter("productId");
        } else {
            productId = (String) request.getAttribute("productId");
        }
        if(ProductDAO.checkIfProductExists(productId)){
            Product singleProduct = ProductDAO.getSingleProductData(productId);
            request.setAttribute("singleProduct", singleProduct);
        }

        request.getRequestDispatcher("/WEB-INF/admin/edit-product.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String product_id = request.getParameter("productId");
        request.setAttribute("productId", product_id);
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

        if(product_id == null){ request.setAttribute("msg", "Nie rozpoznano id edytowanego produktu. Spróbuj ponownie wyszukać produkt " +
                "w menadżerze produktów i zedytuj jego dane jeszcze raz.");
        } else if(product_name == null){ request.setAttribute("msg", "Nie podano nazwy produktu");
        } else if(category == null){ request.setAttribute("msg", "Nie podano kategorii produktu");
        } else if(quantity == null){ request.setAttribute("msg", "Nie podano ilości produktów na stanie");
        } else if(quantity_sold == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(sale_price == null){ request.setAttribute("msg", "Nie podano ilości sprzedanych produktów");
        } else if(date_added == null){ request.setAttribute("msg", "Nie podano daty dodania produktu");
        } else if(price == null){ request.setAttribute("msg", "Nie podano ceny produktu");
        } else if(description == null){ request.setAttribute("msg", "Nie podano opisu produktu");
        } else {
            boolean done = ProductDAO.editGivenProduct(product_id, product_name, category, quantity, quantity_sold, sale_price, date_added, price,
                    description, photoLinkOne, photoLinkTwo, photoLinkThree, photoLinkFour, featured);
            if(done){
                request.setAttribute("msg", "Pomyślnie zedytowano produkt");
            } else {
                request.setAttribute("msg", "Wystąpił problem w trakcie dodawania zedytowanych danych produktu do bazy, spróbuj ponownie, albo zweryfikuj logi serwera");
            }
        }

        doGet(request, response);
    }
}
