package objects;

public class Gallery {

    private int id;
    private String photo_1;
    private String photo_2;
    private String photo_3;
    private String photo_4;

    public Gallery(int id, String photo_1, String photo_2, String photo_3, String photo_4) {
        this.id = id;
        this.photo_1 = photo_1;
        this.photo_2 = photo_2;
        this.photo_3 = photo_3;
        this.photo_4 = photo_4;
    }

    public Gallery(){
        this.id = -1;
        this.photo_1 = null;
        this.photo_2 = null;
        this.photo_3 = null;
        this.photo_4 = null;
    }

    public int getId() {
        return id;
    }
    public String getPhoto_1() {
        return photo_1;
    }
    public String getPhoto_2() { return photo_2; }
    public String getPhoto_3() {
        return photo_3;
    }
    public String getPhoto_4() {
        return photo_4;
    }
}
