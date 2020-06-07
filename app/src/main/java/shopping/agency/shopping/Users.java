package shopping.agency.shopping;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    public String username;
    public String email;

    public Users() {
    }

    public Users(String username, String email) {
        this.username = username;
        this.email = email;
    }

}