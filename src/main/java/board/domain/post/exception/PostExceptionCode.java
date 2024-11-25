package board.domain.post.exception;

public enum PostExceptionCode {
    POST_NOT_FOUND("존재하지 않는 게시글입니다.");

    private final String message;

    PostExceptionCode(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}
