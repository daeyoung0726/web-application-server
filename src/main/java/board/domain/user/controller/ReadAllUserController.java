package board.domain.user.controller;

import board.common.controller.AbstractController;
import board.common.http.HttpRequest;
import board.common.http.HttpResponse;
import board.domain.user.model.User;
import board.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReadAllUserController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ReadUserController.class);

    private final UserService userService;

    public ReadAllUserController() {
        this.userService = UserService.getInstance();
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        List<User> users = userService.findAllUsers();

        log.debug("users : {}", users);

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            sb.append(createJsonData(user));
            if (i < users.size() - 1) {
                sb.append(","); // 마지막 요소가 아니면 콤마 추가
            }
        }
        sb.append("]");

        response.forwardJson(sb.toString());
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