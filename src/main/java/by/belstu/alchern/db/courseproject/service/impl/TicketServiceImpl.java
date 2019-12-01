package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.TicketDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.TicketDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Ticket;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.TicketService;
import by.belstu.alchern.db.courseproject.service.exception.TicketServiceException;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public List<Ticket> getTicketsByUserId(int userId) throws TicketServiceException {
        DAOFactory factory = DAOFactory.getInstance();

        List<Ticket> tickets = null;
        try {
            tickets = factory.getTicketDAO().getTicketsByUserId(userId);
        } catch (TicketDAOException e) {
            throw new TicketServiceException(e);
        }

        return tickets;
    }

    @Override
    public List<Ticket> getFinishedTicketsFromAll(List<Ticket> tickets) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket :
                tickets) {
            if (new Date().getTime() - ticket.getFlight().getDeparture().getTime() > 0) {
                result.add(ticket);
            }
        }
        return result;
    }

    @Override
    public List<Ticket> getUnfinishedTicketsFromAll(List<Ticket> tickets) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket :
                tickets) {
            if (new Date().getTime() - ticket.getFlight().getDeparture().getTime() < 0) {
                result.add(ticket);
            }
        }
        return result;
    }

    @Override
    public void createTicket(User user, String flightId) throws TicketServiceException {
        DAOFactory factory = DAOFactory.getInstance();

        Ticket ticket = new Ticket();
        try {
            ticket.setFlight(factory.getFlightDAO().get(Integer.parseInt(flightId)));
            ticket.setUser(factory.getUserDAO().get(user.getId()));
            ticket.setConfirmed(false);
            factory.getTicketDAO().insert(ticket);
        } catch (DAOException e) {
            throw new TicketServiceException(e);
        }
    }

    @Override
    public void deleteTicket(int idDeleteTicket) throws TicketServiceException {
        try {
            Ticket ticket = new Ticket();
            ticket.setId(idDeleteTicket);
            DAOFactory.getInstance().getTicketDAO().delete(ticket);
        } catch (DAOException e) {
            throw new TicketServiceException(e);
        }
    }

    @Override
    public void confirmTicket(int ticketId) throws TicketServiceException {
        TicketDAO ticketDAO = DAOFactory.getInstance().getTicketDAO();
        try {
            Ticket ticket = ticketDAO.get(ticketId);
            ticket.setConfirmed(true);
            ticketDAO.update(ticket);
        } catch (DAOException e) {
            throw new TicketServiceException(e);
        }
    }
}
