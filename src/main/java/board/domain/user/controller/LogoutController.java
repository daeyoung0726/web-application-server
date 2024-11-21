package board.domain.user.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.common.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    public LogoutController() { }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("/index.html");
        log.debug("logout user session is {}", session.getAttribute("user"));
    }
}
