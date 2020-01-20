package shoppingLogic;

import dao.CartsDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import objects.CartProduct;
import objects.Product;
import util.ConstantValues;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@WebServlet("/koszyk")
public class ShoppingCartServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCartBean = (ShoppingCart) session.getAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY);
        Map<Long, CartProduct> cartContents = shoppingCartBean.getContents();
        if(cartContents.size()>0) {
            ArrayList<CartProduct> currentCartProductsState = CartsDAO.getSpecificProducts(cartContents);
            for(CartProduct p: currentCartProductsState){
                if(p.getQuantity()>p.getProduct().getQuantity()) {
                    p.setQuantity((int) p.getProduct().getQuantity());
                    cartContents.put(p.getProduct().getId(), new CartProduct(p.getProduct(), p.getQuantity()));
                }
            }
            shoppingCartBean.setContents(cartContents);
            request.setAttribute("cartProducts", currentCartProductsState);
            session.setAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY, shoppingCartBean);

        }

        request.getRequestDispatcher("/WEB-INF/pages/koszyk.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCartBean = (ShoppingCart) session.getAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY);
        Product singleProduct;
        ArrayList<CartProduct> cartProducts = new ArrayList<>();

        String disclaim = request.getParameter("disclaimOrder");
        if(disclaim!=null){
            boolean disclaimOrder = Boolean.parseBoolean(disclaim);
            if(disclaimOrder){
                // If somebody want to disclaim order, then you need to flush orders content and put it back inside cart
                String orderId = request.getParameter("orderId");
                cartProducts = OrderDAO.getUnfinishedOrderContentOrderId(orderId);
                OrderDAO.returnUnfinishedOrderContentToStockOrderId(orderId);
                OrderDAO.removeUnfinishedOrderContentOrderId(orderId);
            }
            shoppingCartBean.setContentsArray(cartProducts);
        }

        String productId = request.getParameter("productId");
        String amountString = request.getParameter("amount");
        if(amountString != null && amountString.length() > 0) {
            if(productId != null && productId.length() > 0){
                int amount = Integer.parseInt(amountString);
                if(amount > 0) {
                    singleProduct = ProductDAO.getSingleProductData(productId);
                    long availableQuantity = singleProduct.getQuantity();
                    if (availableQuantity > amount) {
                        shoppingCartBean.changeQuantity(singleProduct, amount);
                    } else {
                        amount = Math.toIntExact(availableQuantity);
                        shoppingCartBean.changeQuantity(singleProduct, amount);
                    }
                } else {
                    singleProduct = ProductDAO.getSingleProductData(productId);
                    shoppingCartBean.removeFromCart(singleProduct);
                }
            }
        }

        String remove = request.getParameter("remove");
        if(remove != null && remove.length() > 0) {
            if(productId != null && productId.length() > 0) {
                singleProduct = ProductDAO.getSingleProductData(productId);
                shoppingCartBean.removeFromCart(singleProduct);
            }
        }

        session.setAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY, shoppingCartBean);
        doGet(request, response);
    }
}
