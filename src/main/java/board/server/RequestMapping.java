package board.server;

import board.common.controller.Controller;
import board.domain.user.controller.CreateUserController;
import board.domain.user.controller.LoginController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/users/sign-up", new CreateUserController());
        controllers.put("/users/sign-in", new LoginController());
    }

    public static Controller getController(String url) {
        return controllers.get(url);
    }
}
