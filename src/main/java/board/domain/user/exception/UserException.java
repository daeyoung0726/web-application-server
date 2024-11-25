package board.domain.user.exception;

public class UserException extends RuntimeException {
    public UserException(UserExceptionCode e) {
        super(e.getMessage());
    }
}
