package by.belstu.alchern.db.courseproject.service.exception;

public class PlainServiceException extends Exception {
    public PlainServiceException() {
    }

    public PlainServiceException(String message) {
        super(message);
    }

    public PlainServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlainServiceException(Throwable cause) {
        super(cause);
    }
}
