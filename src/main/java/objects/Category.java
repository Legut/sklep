package objects;

public class Category {
    private long id;
    private String categoryName;
    private String categoryURL;

    public Category(long id, String categoryName, String categoryURL) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryURL = categoryURL;
    }
    public Category() {
        this.id = 0;
        this.categoryName = "";
        this.categoryURL = "";
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryURL() {
        return categoryURL;
    }
    public void setCategoryURL(String categoryURL) {
        this.categoryURL = categoryURL;
    }
}
