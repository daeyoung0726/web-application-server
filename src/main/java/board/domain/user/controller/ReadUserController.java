package board.domain.user.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.user.model.User;
import board.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadUserController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ReadUserController.class);

    private final UserService userService;

    public ReadUserController() {
        this.userService = UserService.getInstance();
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login.html");
            return;
        }

        User foundUser = userService.findUserById(user.getUsername());
        if (foundUser == null) {
            response.forwardJson("{\"error\": \"User not found\"}");
            return;
        }

        log.debug("read user info : {}", foundUser);

        response.forwardJson(createJsonData(foundUser));
    }

    private String createJsonData(User foundUser) {
        return String.format(
                "{\"username\": \"%s\", \"name\": \"%s\", \"email\": \"%s\"}",
                foundUser.getUsername(),
                foundUser.getName(),
                foundUser.getEmail()
        );
    }
}
