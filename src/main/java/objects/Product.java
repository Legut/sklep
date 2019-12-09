package objects;

public class Product {

    private long id;
    private String product_name;
    private String category;
    private long quantity;
    private long quantity_sold;
    private double sale_price;
    private String date_added;
    private double price;
    private String description;
    private long gallery_id;
    private String imageOne;
    private String imageTwo;
    private String imageThree;
    private String imageFour;

    public Product(long id, String product_name, String category, long quantity, long quantity_sold, double sale_price, String date_added, double price,
                   String description, long gallery_id, String imageOne, String imageTwo, String imageThree, String imageFour) {
        this.id = id;
        this.product_name = product_name;
        this.category = category;
        this.quantity = quantity;
        this.quantity_sold = quantity_sold;
        this.sale_price = sale_price;
        this.date_added = date_added;
        this.price = price;
        this.description = description;
        this.gallery_id = gallery_id;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;
        this.imageFour = imageFour;
    }

    public Product(){
        this.id = -1;
        this.product_name = null;
        this.category = null;
        this.quantity = 0;
        this.quantity_sold = 0;
        this.sale_price = 0;
        this.date_added = null;
        this.price = 0;
        this.description = null;
        this.gallery_id = 0;
        this.imageOne = null;
        this.imageTwo = null;
        this.imageThree = null;
        this.imageFour = null;
    }

    public void setId(int id) { this.id = id; }
    public long getId() { return id; }
    public String getProduct_name() { return product_name; }
    public String getCategory() { return category; }
    public long getQuantity() { return quantity; }
    public long getQuantity_sold() { return quantity_sold; }
    public double getSale_price() { return sale_price; }
    public String getDate_added() { return date_added; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public long getGallery_id() { return gallery_id; }
    public String getImageOne() { return imageOne; }
    public String getImageTwo() { return imageTwo; }
    public String getImageThree() { return imageThree; }
    public String getImageFour() { return imageFour; }
}
