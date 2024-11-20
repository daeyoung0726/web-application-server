package board.database;

import board.domain.post.model.Post;
import board.domain.user.model.User;

public class ConnectionMaker {

    public static DataBase<User> getUserDateBase() {
        return UserDataBase.getInstance();
    }

    public static DataBase<Post> getPostDataBase() {
        return PostDataBase.getInstance();
    }
}
