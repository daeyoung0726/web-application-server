package board.server;

import board.common.controller.Controller;
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
    private static Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/users/sign-up", new CreateUserController());
        controllers.put("/users/sign-in", new LoginController());
        controllers.put("/users/sign-out", new LogoutController());
        controllers.put("/users/me", new ReadUserController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/delete", new DeleteUserController());
        controllers.put("/users", new ReadAllUserController());
    }

    public static Controller getController(String url) {
        return controllers.get(url);
    }
}
