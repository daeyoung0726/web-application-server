package board.server;

import board.common.controller.Controller;
import board.domain.post.controller.CreatePostController;
import board.domain.post.controller.DeletePostController;
import board.domain.post.controller.ReadAllPostController;
import board.domain.post.controller.ReadPostController;
import board.domain.post.controller.UpdatePostController;
import board.domain.user.controller.CreateUserController;
import board.domain.user.controller.DeleteUserController;
import board.domain.user.controller.LoginController;
import board.domain.user.controller.LogoutController;
import board.domain.user.controller.ReadAllUserController;
import board.domain.user.controller.ReadUserController;
import board.domain.user.controller.UpdateUserController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        addUserControllers();
        addPostControllers();
    }

    private static void addUserControllers() {
        controllers.put("/users/sign-up", new CreateUserController());
        controllers.put("/users/sign-in", new LoginController());
        controllers.put("/users/sign-out", new LogoutController());
        controllers.put("/users/me", new ReadUserController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/delete", new DeleteUserController());
        controllers.put("/users", new ReadAllUserController());
    }

    private static void addPostControllers() {
        controllers.put("/posts/create", new CreatePostController());
        controllers.put("/posts", new ReadAllPostController());
        controllers.put("/posts/read", new ReadPostController());
        controllers.put("/posts/update", new UpdatePostController());
        controllers.put("/posts/delete", new DeletePostController());
    }

    public static Controller getController(String url) {
        return controllers.get(url);
    }
}
