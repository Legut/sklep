package shoppingLogic;

import dao.CartsDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import objects.CartProduct;
import org.tuckey.web.filters.urlrewrite.SetAttribute;
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

@WebServlet("/zamowienie")
public class Order extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCartBean = (ShoppingCart) session.getAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY);
        Map<Long, CartProduct> beanCartContents = shoppingCartBean.getContents();
        if(beanCartContents.size()>0) {
            ArrayList<CartProduct> cartProducts = CartsDAO.getSpecificProducts(beanCartContents);
            request.setAttribute("cartProducts", cartProducts);
        }

        String userId = (String) session.getAttribute("user_id");
        if(userId!=null && userId.length()>0){
            ArrayList<CartProduct> orderContents = OrderDAO.getUnfinishedOrderContent(userId);
            String orderId = OrderDAO.getUnfinishedOrderId(userId);
            request.setAttribute("orderContents", orderContents);
            request.setAttribute("orderId", orderId);
        }

        request.getRequestDispatcher("/WEB-INF/pages/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCartBean = (ShoppingCart) session.getAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY);
        String userId = (String) session.getAttribute("user_id");

        // TODO: if user_id / obsługa sytuacji, gdy uzytkownik nie jest zarejestrowany
        if(shoppingCartBean!=null) {
            Map<Long, CartProduct> cartMap = shoppingCartBean.getContents();
            if(cartMap!=null && cartMap.size()>0) {
                // You need to delete content of previous order (from database) if it exists. It is important
                // to do it before getting current products state
                boolean goFurther = OrderDAO.returnUnfinishedOrderContentToStock(userId);

                // Get current products state form DB (get only those products data, which are inside the cart)
                ArrayList<CartProduct> currentProductsState = CartsDAO.getSpecificProducts(cartMap);

                for(CartProduct p: currentProductsState){
                    if(p.getQuantity()>p.getProduct().getQuantity()) {
                        p.setQuantity((int) p.getProduct().getQuantity());
                        cartMap.put(p.getProduct().getId(), new CartProduct(p.getProduct(), p.getQuantity()));
                    }
                }
                shoppingCartBean.setContents(cartMap);
//                request.setAttribute("cartProducts", currentProductsState);

                if(!goFurther) {
                    request.setAttribute("msg", "Coś poszło nie tak przy składaniu zamówienia. Skontaktuj się z administratorem lub działem obsługi klienta");
                    System.out.println("Coś poszło nie tak przy zwracaniu zawartości zamówienia do tabeli products Order.doPost().OrdersDAO.returnUnfinishedOrderContentToStock()");
                } else {
                    // If data has been returned from orders_content to products, than you can delete data about orders
                    goFurther = OrderDAO.removeUnfinishedOrderContent(userId);
                    if(!goFurther) {
                        request.setAttribute("msg", "Coś poszło nie tak przy składaniu zamówienia. Skontaktuj się z administratorem lub działem obsługi klienta");
                        System.out.println("Coś poszło nie tak przy czyszczeniu zawartości zamówienia Order.doPost().OrdersDAO.removeUnfinishedOrderContent()");
                    } else {
                        // Finally prepare order from given cart. Book items and create order in database.
                        goFurther = OrderDAO.prepareOrder(userId, cartMap);

                        if(!goFurther){
                            request.setAttribute("msg", "Coś poszło nie tak przy składaniu zamówienia. Skontaktuj się z administratorem lub działem obsługi klienta");
                            System.out.println("Coś poszło nie tak przy przygotowywaniu zamówienia Order.doPost().OrdersDAO.prepareOrder()");
                        } else {
                            request.setAttribute("msg", "Zanim przejdziesz dalej sprawdź czy wszystkie informacje są poprawne.");
                        }
                    }
                }
            }
        }
        shoppingCartBean.clearCart();
        session.setAttribute(ConstantValues.SHOPPING_CART_BEAN_SESION_KEY, shoppingCartBean);
        doGet(request, response);
    }

    private static Map<Long, CartProduct> changeCartIfNeeded(ArrayList<CartProduct> currentProductsState, Map<Long, CartProduct> cartMap) {
        // You need to iterate through the whole currentProductsState list and check for every 
        // single element if the current quantity of a product is enough to make an order. If not,
        // than you still want to prepare order, but with maximum quantity values. And you also
        // have to inform client about the change between cart in /koszyk page and in /zamowienie page

        for(int i=0; i<currentProductsState.size(); i++){
            CartProduct currentProduct = currentProductsState.get(i);
            cartMap.put(currentProduct.getProduct().getId(), currentProduct);
        }

        return cartMap;
    }
}
