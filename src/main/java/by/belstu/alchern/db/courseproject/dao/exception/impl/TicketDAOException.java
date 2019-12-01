package by.belstu.alchern.db.courseproject.dao.exception.impl;

import by.belstu.alchern.db.courseproject.dao.exception.DAOException;

public class TicketDAOException extends DAOException {

    public TicketDAOException() {
    }

    public TicketDAOException(String message) {
        super(message);
    }

    public TicketDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketDAOException(Throwable cause) {
        super(cause);
    }
}
