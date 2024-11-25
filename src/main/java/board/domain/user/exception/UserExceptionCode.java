package board.domain.user.exception;

public enum UserExceptionCode {
    USER_NOT_FOUND("존재하지 않는 회원입니다.");

    private final String message;

    UserExceptionCode(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}
