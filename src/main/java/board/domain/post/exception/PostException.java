package board.domain.post.exception;

public class PostException extends RuntimeException {
    public PostException(PostExceptionCode e) {
        super(e.getMessage());
    }
}
