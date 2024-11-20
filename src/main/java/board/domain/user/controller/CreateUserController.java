package board.domain.user.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.user.model.User;
import board.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    private final UserService userService;

    public CreateUserController() {
        this.userService = UserService.getInstance();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        User user = new User(
                request.getParam("username"),
                request.getParam("password"),
                request.getParam("name"),
                request.getParam("email")
                );

        log.debug("user : {}", user);
        userService.saveUser(user);
        response.sendRedirect("/index.html");
    }
}
