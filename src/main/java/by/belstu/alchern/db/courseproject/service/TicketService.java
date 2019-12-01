package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.Ticket;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.exception.TicketServiceException;

import java.util.List;

public interface TicketService {
    List<Ticket> getByPageNum(int pageNum) throws TicketServiceException;

    List<Ticket> getTicketsByUserId(int userId) throws TicketServiceException;

    List<Ticket> getFinishedTicketsFromAll(List<Ticket> tickets);

    List<Ticket> getUnfinishedTicketsFromAll(List<Ticket> tickets);

    void createTicket(User user, String flightId) throws TicketServiceException;

    void deleteTicket(int idDeleteTicket) throws TicketServiceException;

    void confirmTicket(int ticketId) throws TicketServiceException;
}
