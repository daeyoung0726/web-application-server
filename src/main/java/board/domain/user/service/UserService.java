package board.domain.user.service;

import board.domain.user.model.User;
import board.domain.user.repository.UserDao;

import java.util.List;

public class UserService {
    private static final UserService userService = new UserService();
    private final UserDao userDao;

    private UserService() {
        this.userDao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        return userService;
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public User findUserById(String username) {
        return userDao.findUserById(username);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public void updateUser(String username, User user) {
        userDao.updateUser(username, user);
    }

    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }
}
