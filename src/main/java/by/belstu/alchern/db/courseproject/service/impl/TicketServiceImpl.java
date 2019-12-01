package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.impl.TicketDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Ticket;
import by.belstu.alchern.db.courseproject.service.TicketService;
import by.belstu.alchern.db.courseproject.service.exception.TicketServiceException;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    @Override
    public List<Ticket> getByPageNum(int pageNum) throws TicketServiceException {
        List<Ticket> tickets = null;
        try {
            tickets = DAOFactory.getInstance().getTicketDAO().getByRange((pageNum-1) * 5, (pageNum-1) * 5 + 10 );
        } catch (TicketDAOException e) {
            throw new TicketServiceException(e);
        }
        return  tickets;
    }
}
