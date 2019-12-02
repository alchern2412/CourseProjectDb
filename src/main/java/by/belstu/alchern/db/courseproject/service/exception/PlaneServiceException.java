package by.belstu.alchern.db.courseproject.service.exception;

public class PlaneServiceException extends Exception {
    public PlaneServiceException() {
    }

    public PlaneServiceException(String message) {
        super(message);
    }

    public PlaneServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaneServiceException(Throwable cause) {
        super(cause);
    }
}
