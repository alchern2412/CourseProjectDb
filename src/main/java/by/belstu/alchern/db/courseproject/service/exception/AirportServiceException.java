package by.belstu.alchern.db.courseproject.service.exception;

public class AirportServiceException extends Exception {
    public AirportServiceException() {
    }

    public AirportServiceException(String message) {
        super(message);
    }

    public AirportServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportServiceException(Throwable cause) {
        super(cause);
    }
}
