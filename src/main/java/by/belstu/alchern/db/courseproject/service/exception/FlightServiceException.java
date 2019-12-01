package by.belstu.alchern.db.courseproject.service.exception;

public class FlightServiceException extends Exception {
    public FlightServiceException() {
    }

    public FlightServiceException(String message) {
        super(message);
    }

    public FlightServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlightServiceException(Throwable cause) {
        super(cause);
    }
}
