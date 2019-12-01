package by.belstu.alchern.db.courseproject.service.exception;

public class TicketServiceException extends Exception {
    public TicketServiceException() {
    }

    public TicketServiceException(String message) {
        super(message);
    }

    public TicketServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketServiceException(Throwable cause) {
        super(cause);
    }
}
