package board.domain.user.model;

public class User {
    private String username;
    private String password;
    private String name;
    private String email;

    public User(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public void updateInfo(User user) {
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    @Override
    public String toString() {
        return "User [username=" + username +
                ", password=" + password +
                ", name=" + name +
                ", email=" + email +
                "]";
    }
}
