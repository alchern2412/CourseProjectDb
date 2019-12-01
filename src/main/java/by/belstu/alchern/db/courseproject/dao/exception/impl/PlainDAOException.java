package by.belstu.alchern.db.courseproject.dao.exception.impl;

import by.belstu.alchern.db.courseproject.dao.exception.DAOException;

public class PlainDAOException extends DAOException {

    public PlainDAOException() {
    }

    public PlainDAOException(String message) {
        super(message);
    }

    public PlainDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlainDAOException(Throwable cause) {
        super(cause);
    }
}
