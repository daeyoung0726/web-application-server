package board.domain.user.repository;

import board.database.JdbcTemplate;
import board.database.RowMapper;
import board.domain.user.model.User;

import java.util.List;

public class UserDao {
    private static final UserDao userDao = new UserDao();

    private final JdbcTemplate jdbcTemplate;

    private UserDao() {
        this.jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static UserDao getInstance() {
        return userDao;
    }

    private final RowMapper<User> userMapper = rs -> new User(
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email")
    );

    public void saveUser(User user) {
        String sql = "INSERT INTO \"User\"(username, password, name, email) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
        );
    }

    public User findUserById(String username) {
        String sql = "SELECT * FROM \"User\" WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, userMapper, username);
    }

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM \"User\"";
        return jdbcTemplate.query(sql, userMapper);
    }

    public void updateUser(String username, User user) {
        String sql = "UPDATE \"User\" SET password = ?, name = ?, email = ? WHERE username = ?";
        jdbcTemplate.update(
                sql,
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                username
        );
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM \"User\" WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }
}
