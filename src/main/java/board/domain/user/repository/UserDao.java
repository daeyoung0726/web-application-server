package board.domain.user.repository;

import board.database.ConnectionManager;
import board.database.exception.DataAccessException;
import board.domain.user.exception.UserException;
import board.domain.user.exception.UserExceptionCode;
import board.domain.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final UserDao userDao = new UserDao();

    private UserDao() {}

    public static UserDao getInstance() {
        return userDao;
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO \"User\"(username, password, name, email) VALUES(?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public User findUserById(String username) {
        String sql = "SELECT * FROM \"User\" WHERE username = ?";
        User user = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }

            rs.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        if (user == null)
            throw new UserException(UserExceptionCode.USER_NOT_FOUND);

        return user;
    }

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM \"User\"";
        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                users.add(user);
            }

            rs.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

        return users;
    }

    public void updateUser(String username, User user) {
        String sql = "UPDATE \"User\" SET password = ?, name = ?, email = ? WHERE username = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, username);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM \"User\" WHERE username = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
