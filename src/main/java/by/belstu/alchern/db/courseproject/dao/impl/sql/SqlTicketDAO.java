package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.TicketDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.RoleDAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.TicketDAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.UserDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Ticket;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlTicketDAO implements TicketDAO {
    private static final String GET_TICKET = "{call usp_ticketsSelect(?)}";
    private static final String GET_TICKETS = "{call usp_ticketsSelect(null)}";
    private static final String INSERT_TICKET = "{call usp_ticketsInsert(?, ?, ?)}";
    private static final String UPDATE_TICKET = "{call usp_ticketsUpdate(?, ?, ?, ?)}";
    private static final String DELETE_TICKET = "{call usp_ticketsDelete(?)}";

    // services
    private static final String GET_BY_RANGE = "{call usp_ticketsSelectByRange(?, ?)}";


    @Override
    public Ticket get(int id) throws TicketDAOException {
        Ticket ticket = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_TICKET);
            callableStatement.setInt(1, id);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    ticket = new Ticket();
                    ticket.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    ticket.setUser(DAOFactory.getInstance().getUserDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_USER_ID)
                    ));
                    ticket.setFlight(DAOFactory.getInstance().getFlightDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_FLIGHT_ID)
                    ));
                    ticket.setConfirmed(resultSet.getBoolean(DbParameterName.REQ_TICKET_CONFIRMED));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new TicketDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return ticket;
    }

    @Override
    public List<Ticket> getAll() throws TicketDAOException {
        List<Ticket> tickets = new ArrayList<Ticket>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_TICKETS);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    ticket.setUser(DAOFactory.getInstance().getUserDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_USER_ID)
                    ));
                    ticket.setFlight(DAOFactory.getInstance().getFlightDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_FLIGHT_ID)
                    ));
                    ticket.setConfirmed(resultSet.getBoolean(DbParameterName.REQ_TICKET_CONFIRMED));

                    tickets.add(ticket);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new TicketDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return tickets;
    }

    @Override
    public void insert(Ticket ticket) throws TicketDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT_TICKET);
            callableStatement.setInt(1, ticket.getFlight().getId());
            callableStatement.setInt(2, ticket.getUser().getId());
            callableStatement.setBoolean(3, ticket.isConfirmed());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    ticket.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    ticket.setUser(DAOFactory.getInstance().getUserDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_USER_ID)
                    ));
                    ticket.setFlight(DAOFactory.getInstance().getFlightDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_FLIGHT_ID)
                    ));
                    ticket.setConfirmed(resultSet.getBoolean(DbParameterName.REQ_TICKET_CONFIRMED));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new TicketDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Ticket ticket) throws TicketDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE_TICKET);
            callableStatement.setInt(1, ticket.getId());
            callableStatement.setInt(2, ticket.getFlight().getId());
            callableStatement.setInt(3, ticket.getUser().getId());
            callableStatement.setBoolean(4, ticket.isConfirmed());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    ticket.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    ticket.setUser(DAOFactory.getInstance().getUserDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_USER_ID)
                    ));
                    ticket.setFlight(DAOFactory.getInstance().getFlightDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_FLIGHT_ID)
                    ));
                    ticket.setConfirmed(resultSet.getBoolean(DbParameterName.REQ_TICKET_CONFIRMED));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new TicketDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Ticket ticket) throws TicketDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE_TICKET);
            callableStatement.setInt(1, ticket.getId());
            callableStatement.execute();
//
//            if (hadResults) {
//                resultSet = callableStatement.getResultSet();
//                if (resultSet.next()) {
//                    ticket = new Ticket();
//                    ticket.setId(resultSet.getInt(DbParameterName.REQ_ID));
//                    ticket.setUser(DAOFactory.getInstance().getUserDAO().get(
//                            resultSet.getInt(DbParameterName.REQ_TICKET_USER_ID)
//                    ));
//                    ticket.setFlight(DAOFactory.getInstance().getFlightDAO().get(
//                            resultSet.getInt(DbParameterName.REQ_TICKET_FLIGHT_ID)
//                    ));
//                    ticket.setConfirmed(resultSet.getBoolean(DbParameterName.REQ_TICKET_CONFIRMED));
//                }
//            }
        } catch (SQLException e) {
            throw new TicketDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }

    @Override
    public List<Ticket> getByRange(int x1, int x2) throws TicketDAOException {
        List<Ticket> tickets = new ArrayList<Ticket>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_BY_RANGE);
            callableStatement.setInt(1, x1);
            callableStatement.setInt(2, x2);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    ticket.setUser(DAOFactory.getInstance().getUserDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_USER_ID)
                    ));
                    ticket.setFlight(DAOFactory.getInstance().getFlightDAO().get(
                            resultSet.getInt(DbParameterName.REQ_TICKET_FLIGHT_ID)
                    ));
                    ticket.setConfirmed(resultSet.getBoolean(DbParameterName.REQ_TICKET_CONFIRMED));

                    tickets.add(ticket);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new TicketDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return tickets;
    }
}
