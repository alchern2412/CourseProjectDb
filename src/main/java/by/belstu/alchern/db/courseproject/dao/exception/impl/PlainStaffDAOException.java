package by.belstu.alchern.db.courseproject.dao.exception.impl;

import by.belstu.alchern.db.courseproject.dao.exception.DAOException;

public class PlainStaffDAOException extends DAOException {

    public PlainStaffDAOException() {
    }

    public PlainStaffDAOException(String message) {
        super(message);
    }

    public PlainStaffDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlainStaffDAOException(Throwable cause) {
        super(cause);
    }
}
