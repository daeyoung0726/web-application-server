package board.domain.user.controller;

import board.domain.user.service.UserService;

public class UserSaveController {
    private final UserService userService;

    public UserSaveController() {
        this.userService = UserService.getInstance();
    }
}
