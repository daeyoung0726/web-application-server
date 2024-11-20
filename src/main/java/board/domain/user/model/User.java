package board.domain.user.model;

public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public void updateInfo(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    @Override
    public String toString() {
        return "User [id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", name=" + name +
                ", email=" + email +
                "]";
    }
}
