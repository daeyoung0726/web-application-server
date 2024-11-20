package board.domain.user.repository;

import board.database.ConnectionMaker;
import board.database.DataBase;
import board.domain.user.model.User;

import java.util.List;

public class UserDao {
    private static final UserDao userDao = new UserDao();
    private final DataBase<User> userDataBase;

    private UserDao() {
        this.userDataBase = ConnectionMaker.getUserDateBase();
    }

    public static UserDao getInstance() {
        return userDao;
    }

    public void saveUser(User user) {
        userDataBase.add(user);
    }

    public User findUserById(String username) {
        return userDataBase.findById(username);
    }

    public List<User> findAllUsers() {
        return userDataBase.findAll();
    }

    public void updateUser(String username, User user) {
        userDataBase.updateById(username, user);
    }

    public void deleteUser(String username) {
        userDataBase.deleteById(username);
    }
}
