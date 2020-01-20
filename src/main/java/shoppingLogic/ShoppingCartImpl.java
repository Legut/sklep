package shoppingLogic;

import dao.ProductDAO;
import objects.CartProduct;
import objects.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class ShoppingCartImpl implements ShoppingCart {
    private Map<Long, CartProduct> cart;

    @PostConstruct
    private void init(){
        cart = new HashMap<>();
    }

    @Override
    public void initalize(){
        cart = new HashMap<>();
    }

    @Override
    public void addToCart(String productId, String quantityToAddStr) {
        // converting data from String to specific types
        Integer quantityToAdd = Integer.parseInt(quantityToAddStr);
        Product product = ProductDAO.getSingleProductData(productId);

        // It's needed to check if product is already in cart to avoid
        // multiplying products instances inside single cart
        boolean isAlreadyInCart = false;
        for (Map.Entry<Long, CartProduct> entry : cart.entrySet()) {
            if (entry.getKey() == product.getId()) {
                isAlreadyInCart = true;
                break;
            }
        }

        if (isAlreadyInCart) {
            // You need to check if its possible to add given amount. Someone could already
            // have that product in the cart. So the quantity in db must be higher than sum
            // of quantityToAdd and quantity in cart

            int finalQuantity = quantityToAdd + cart.get(product.getId()).getQuantity();

            if(finalQuantity < product.getQuantity()) {
                // Changing quantity value in provided product object

                product.setQuantity(product.getQuantity() - quantityToAdd);
                CartProduct temp = new CartProduct(product, finalQuantity);
                cart.put(product.getId(), temp);
            } else if (finalQuantity > product.getQuantity()) {
                // quantity value need to be improved, because the final expected amount is higher
                // than quantity in stock, which is a problem here. So you want the max possible value,
                // which is actually whole stock.

                product.setQuantity(product.getQuantity());
                CartProduct temp = new CartProduct(product, (int) product.getQuantity());
                cart.put(product.getId(), temp);
            }
        } else {
            // If it is not already in cart than you only put new product inside

            CartProduct temp = new CartProduct(product, quantityToAdd);
            cart.put(product.getId(), temp);
        }
    }

    @Override
    public void changeQuantity(Product product, Integer expectedQuantity) {
        // You want to set expected quantity as final quantity. However, it may be lower than
        // quantity before change. It means you have to do some maths, to avoid problems
        int currentQuantity = cart.get(product.getId()).getQuantity();
        currentQuantity += expectedQuantity - currentQuantity;

        CartProduct temp = new CartProduct(product, currentQuantity);
        cart.put(product.getId(), temp);
    }

    @Override
    public void removeFromCart(Product product) {
        // It's simple. Key and value to remove entry from map. So you need to get the CartProduct object from cart Map
        // by id of provided product. Then you clear data connected with provided product using standard .remove();
        CartProduct temp = cart.get(product.getId());
        boolean result = cart.remove(product.getId(), temp);
        if(result) {
            System.out.println("Pomyślnie usunięto produkt z koszyka.");
        } else {
            System.out.println("Nie udało się usunąć produktu z koszyka.");
        }
    }

    @Override
    public Map<Long, CartProduct> getContents(){
        return cart;
    }

    @Override
    public void setContents(Map<Long, CartProduct> content){
        cart = content;
    }

    @Override
    public void setContentsArray(ArrayList<CartProduct> content){
        for(CartProduct p: content){
            cart.put(p.getProduct().getId(),p);
        }
    }

    @Override
    public void addAnotherCartsContent(Map<Long, CartProduct> cartFromDB){
        for(Map.Entry<Long, CartProduct> dbCartEntry: cartFromDB.entrySet()) {
            String productId = String.valueOf(dbCartEntry.getValue().getProduct().getId());
            String quantityToAdd = String.valueOf(dbCartEntry.getValue().getQuantity());
            addToCart(productId, quantityToAdd);
        }
    }

    @Override
    public void clearCart(){
        cart.clear();
    }

    @Remove
    public void remove(){
        cart=null;
    }
}
