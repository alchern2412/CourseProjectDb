package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.Ticket;
import by.belstu.alchern.db.courseproject.service.exception.TicketServiceException;

import java.util.List;

public interface TicketService {
    List<Ticket> getByPageNum(int pageNum) throws TicketServiceException;
}
