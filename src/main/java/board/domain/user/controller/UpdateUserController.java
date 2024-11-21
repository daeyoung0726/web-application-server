package board.domain.user.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.user.model.User;
import board.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateUserController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    private final UserService userService;

    public UpdateUserController() {
        this.userService = UserService.getInstance();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        User userUpdateInfo = new User(
                null,
                request.getParam("password"),
                request.getParam("name"),
                request.getParam("email")
        );

        userService.updateUser(user.getUsername(), userUpdateInfo);

        request.getSession().setAttribute("user", user);
        log.debug("updated user info : {}", user);
        response.sendRedirect("/user/my.html");
    }
}