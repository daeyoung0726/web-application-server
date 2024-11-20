package board.database;

import board.domain.user.controller.CreateUserController;
import board.domain.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class UserDataBase implements DataBase<User> {
    private static final Logger log = LoggerFactory.getLogger(UserDataBase.class);

    private static final Map<String, User> userMap = new ConcurrentHashMap<>();

    private static final UserDataBase userDatabase = new UserDataBase();

    private UserDataBase() {}

    public static UserDataBase getInstance() {
        return userDatabase;
    }

    @Override
    public void add(User user) {
        userMap.put(user.getUsername(), user);

        log.debug("saved User : {}", userMap.get(user.getUsername()));
    }

    @Override
    public User findById(Object id) {
        return userMap.get((String) id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void updateById(Object id, User user) {
        User foundUser = userMap.get((String) id);

        if (foundUser == null)
            throw new IllegalArgumentException("존재하지 않는 계정입니다.");
        foundUser.updateInfo(user);

        log.debug("updated User : {}", userMap.get(user.getUsername()));
    }

    @Override
    public void deleteById(Object id) {
        userMap.remove((String) id);
    }
}
