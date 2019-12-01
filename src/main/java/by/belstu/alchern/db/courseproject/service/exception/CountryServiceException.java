package by.belstu.alchern.db.courseproject.service.exception;

public class CountryServiceException extends Exception {
    public CountryServiceException() {
    }

    public CountryServiceException(String message) {
        super(message);
    }

    public CountryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryServiceException(Throwable cause) {
        super(cause);
    }
}
