package by.belstu.alchern.db.courseproject.service.exception;

public class PlainStuffServiceException extends Exception {
    public PlainStuffServiceException() {
    }

    public PlainStuffServiceException(String message) {
        super(message);
    }

    public PlainStuffServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlainStuffServiceException(Throwable cause) {
        super(cause);
    }
}
