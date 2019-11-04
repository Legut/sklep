package objects;

public class User {
    int id;
    String user_login;
    String user_pass;
    String first_name;
    String last_name;
    String user_email;
    String user_registred;
    String user_activation;
    String user_role;

    public User(int id, String user_login, String user_pass, String first_name, String last_name, String user_email, String user_registred, String user_activation, String user_role) {
        this.id = id;
        this.user_login = user_login;
        this.user_pass = user_pass;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_email = user_email;
        this.user_registred = user_registred;
        this.user_activation = user_activation;
        this.user_role = user_role;
    }

    public User(){
        this.id = -1;
        this.user_login = null;
        this.user_pass = null;
        this.first_name = null;
        this.last_name = null;
        this.user_email = null;
        this.user_registred = null;
        this.user_activation = null;
        this.user_role = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getUser_registred() {
        return user_registred;
    }

    public void setUser_registred(String user_registred) {
        this.user_registred = user_registred;
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
}
