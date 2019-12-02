package objects;

public class Product {

    private long id;
    private String product_name;
    private String category;
    private long quantity;
    private long quantity_sold;
    private boolean on_sale;
    private String date_added;
    private double price;
    private String description;
    private long gallery_id;

    public Product(long id, String product_name, String category, long quantity, long quantity_sold, boolean on_sale, String date_added, double price, String description, long gallery_id) {
        this.id = id;
        this.product_name = product_name;
        this.category = category;
        this.quantity = quantity;
        this.quantity_sold = quantity_sold;
        this.on_sale = on_sale;
        this.date_added = date_added;
        this.price = price;
        this.description = description;
        this.gallery_id = gallery_id;
    }

    public Product(){
        this.id = -1;
        this.product_name = null;
        this.category = null;
        this.quantity = 0;
        this.quantity_sold = 0;
        this.on_sale = false;
        this.date_added = null;
        this.price = 0;
        this.description = null;
        this.gallery_id = 0;
    }

    public void setId(int id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return product_name;
    }
    public String getCategory() {
        return category;
    }
    public long getQuantity() {
        return quantity;
    }
    public long getQuantity_sold() {
        return quantity_sold;
    }
    public Boolean getOn_sale() {
        return on_sale;
    }
    public String getDate_added() {
        return date_added;
    }
    public Double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public long getGallery_id() {
        return gallery_id;
    }
}
