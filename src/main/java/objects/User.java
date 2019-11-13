package objects;

public class User {
    private long id;
    private String user_login;
    private String user_pass;
    private String first_name;
    private String last_name;
    private String user_email;
    private String user_registered;
    private String user_activation;
    private String user_role;
    private String birth_date;

    public User(long id, String user_login, String user_pass, String first_name, String last_name, String user_email, String user_registered, String user_activation, String user_role, String birth_date) {
        this.id = id;
        this.user_login = user_login;
        this.user_pass = user_pass;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_email = user_email;
        this.user_registered = user_registered;
        this.user_activation = user_activation;
        this.user_role = user_role;
        this.birth_date = birth_date;
    }

    public User(){
        this.id = -1;
        this.user_login = null;
        this.user_pass = null;
        this.first_name = null;
        this.last_name = null;
        this.user_email = null;
        this.user_registered = null;
        this.user_activation = null;
        this.user_role = null;
        this.birth_date = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_registered() {
        return user_registered;
    }

    public void setUser_registered(String user_registered) {
        this.user_registered = user_registered;
    }

    public String getUser_activation() {
        return user_activation;
    }

    public void setUser_activation(String user_activation) {
        this.user_activation = user_activation;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
}
