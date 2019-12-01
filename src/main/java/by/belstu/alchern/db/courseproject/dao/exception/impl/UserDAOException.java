package by.belstu.alchern.db.courseproject.dao.exception.impl;

import by.belstu.alchern.db.courseproject.dao.exception.DAOException;

public class UserDAOException extends DAOException {

    public UserDAOException() {
    }

    public UserDAOException(String message) {
        super(message);
    }

    public UserDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDAOException(Throwable cause) {
        super(cause);
    }
}
