package by.belstu.alchern.db.courseproject.dao;

import by.belstu.alchern.db.courseproject.dao.exception.impl.TicketDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Ticket;

import java.util.List;

public interface TicketDAO extends EntityDAO<Ticket> {
    List<Ticket> getByRange(int x1, int x2) throws TicketDAOException;
}
