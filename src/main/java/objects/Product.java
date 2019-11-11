package objects;

public class Product {

    private int id;
    private String name;
    private String category;
    private int quantity;
    private int quantity_sold;
    private boolean on_sale;
    private String date_added;
    private double price;
    private String description;
    private int gallery_id;

    public Product(int id, String name, String category, int quantity, int quantity_sold, boolean on_sale, String date_added, double price, String description, int gallery_id) {
        this.id = id;
        this.name = name;
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
        this.name = null;
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
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getQuantity_sold() {
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
    public int getGallery_id() {
        return gallery_id;
    }
}
