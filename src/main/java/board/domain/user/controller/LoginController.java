package board.domain.user.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.common.http.HttpSession;
import board.domain.user.model.User;
import board.domain.user.service.UserService;

public class LoginController extends AbstractController {
    private final UserService userService;

    public LoginController() {
        this.userService = UserService.getInstance();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        User foundUser = userService.findUserById(request.getParam("username"));
        if (foundUser != null) {
            if (foundUser.matchPassword(request.getParam("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", foundUser);
                response.sendRedirect("/index.html");
            } else {
                response.sendRedirect("/user/signin.html");
            }
        } else {
            response.sendRedirect("/user/signin.html");
        }
    }
}
