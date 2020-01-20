package pages;

import shoppingLogic.ShoppingCart;
import dao.ProductDAO;
import objects.CartProduct;
import objects.Product;
import util.ConstantValues;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/sklep/produkt")
public class SingleProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Product product = ProductDAO.getSingleProductData(id);
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/pages/single-product.jsp").forward(request, response);
            } else {
                response.sendRedirect("/sklep");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/sklep");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCartBean = (ShoppingCart) session.getAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY);

        String id = request.getParameter("id");
        String amountString = request.getParameter("amount");

        if(amountString != null && amountString.length() > 0) {
            if(id != null && id.length() > 0){
                shoppingCartBean.addToCart(id, amountString);
            }
        }

        session.setAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY, shoppingCartBean);
        Map<Long, CartProduct> beanCartContents = shoppingCartBean.getContents();
        ArrayList<CartProduct> cartProducts = new ArrayList<>(beanCartContents.values());
        session.setAttribute("cartProducts", cartProducts);
        doGet(request, response);
    }
}