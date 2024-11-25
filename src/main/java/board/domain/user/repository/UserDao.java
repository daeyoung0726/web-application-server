package board.domain.user.repository;

import board.database.ConnectionManager;
import board.database.PreparedStatementSetter;
import board.database.RowMapper;
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

    private final RowMapper<User> userMapper = rs -> new User(
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email")
    );

    public void saveUser(User user) {
        String sql = "INSERT INTO \"User\"(username, password, name, email) VALUES(?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            PreparedStatementSetter pstmtSetter =
                    runPreparedStatementSetter(
                            user.getUsername(),
                            user.getPassword(),
                            user.getName(),
                            user.getEmail()
                    );
            pstmtSetter.setParameters(pstmt);

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

            PreparedStatementSetter pstmtSetter = runPreparedStatementSetter(username);
            pstmtSetter.setParameters(pstmt);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = userMapper.mapRow(rs);
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
                User user = userMapper.mapRow(rs);
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

            PreparedStatementSetter pstmtSeter =
                    runPreparedStatementSetter(
                            user.getPassword(),
                            user.getName(),
                            user.getEmail(),
                            username
                    );
            pstmtSeter.setParameters(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM \"User\" WHERE username = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            PreparedStatementSetter pstmtSetter = runPreparedStatementSetter(username);
            pstmtSetter.setParameters(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private PreparedStatementSetter runPreparedStatementSetter(Object... parameters) {
        return createPreparedStatementSetter(parameters);
    }

    private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
        return pstmt -> {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
        };
    }
}
