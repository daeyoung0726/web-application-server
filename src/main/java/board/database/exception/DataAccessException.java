package board.database.exception;

public class DataAccessException extends RuntimeException {

    public DataAccessException(Throwable e) {
        super(e);
    }
}
