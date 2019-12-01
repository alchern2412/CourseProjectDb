package by.belstu.alchern.db.courseproject.service.exception;

public class WorkerServiceException extends Exception {
    public WorkerServiceException() {
    }

    public WorkerServiceException(String message) {
        super(message);
    }

    public WorkerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkerServiceException(Throwable cause) {
        super(cause);
    }
}
