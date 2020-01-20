package objects;

public class CartProduct {
    Product product;
    Integer quantity;

    public CartProduct(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartProduct() {
        this.product = new Product();
        this.quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
