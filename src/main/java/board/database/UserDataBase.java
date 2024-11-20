package board.database;

import board.domain.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserDataBase implements DataBase<User> {
    private static final Map<Long, User> userMap = new ConcurrentHashMap<>();
    private static Long idCounter = 1L;

    private static final UserDataBase userDatabase = new UserDataBase();

    private UserDataBase() {}

    public static UserDataBase getInstance() {
        return userDatabase;
    }

    @Override
    public void add(User user) {
        user.setId(idCounter++);
        userMap.put(user.getId(), user);
    }

    @Override
    public User findById(Long id) {
        return userMap.get(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void updateById(Long id, User user) {
        User foundUser = userMap.get(id);

        if (foundUser == null)
            throw new IllegalArgumentException("존재하지 않는 계정입니다.");
        foundUser.updateInfo(user);
    }

    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
    }
}
