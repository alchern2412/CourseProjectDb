package by.belstu.alchern.db.courseproject.dao.exception.impl;

import by.belstu.alchern.db.courseproject.dao.exception.DAOException;

public class CountryDAOException extends DAOException {

    public CountryDAOException() {
    }

    public CountryDAOException(String message) {
        super(message);
    }

    public CountryDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryDAOException(Throwable cause) {
        super(cause);
    }
}
