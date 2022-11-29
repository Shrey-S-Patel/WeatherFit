package weather.efk;

public class User {
    public String username;
    public String email;
    public String fullname;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String fullname) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
    }
}
