package shoppingLogic;

import objects.CartProduct;
import objects.Product;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.Map;

@Local
public interface ShoppingCart {
    public void clearCart();
    public void initalize();
    public void setContentsArray(ArrayList<CartProduct> content);
    public void addToCart(String productId, String quantityToAddStr);
    public void removeFromCart(Product product);
    public Map<Long, CartProduct> getContents();
    public void setContents(Map<Long, CartProduct> content);
    public void remove();
    public void changeQuantity(Product product, Integer quantity);
    public void addAnotherCartsContent(Map<Long, CartProduct> cartFromDB);
}
