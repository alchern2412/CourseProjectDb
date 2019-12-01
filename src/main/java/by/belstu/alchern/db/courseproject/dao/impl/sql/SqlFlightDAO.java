package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.FlightDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.FlightDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Flight;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlFlightDAO implements FlightDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlFlightDAO.class.getSimpleName());

    private static final String GET = "{call usp_flightsSelect(?)}";
    private static final String GET_ALL = "{call usp_flightsSelect(null)}";
    private static final String INSERT = "{call usp_flightsInsert(?, ?, ?, ?, ?, ?)}";
    private static final String UPDATE = "{call usp_flightsUpdate(?, ?, ?, ?, ?, ?, ?)}";
    private static final String DELETE = "{call usp_flightsDelete(?)}";

    @Override
    public Flight get(int id) throws FlightDAOException {
        Flight flight = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET);
            callableStatement.setInt(1, id);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    flight = new Flight();
                    flight.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    flight.setFrom_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_FROM_AIRPORT_ID
                    )));
                    flight.setTo_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_TO_AIRPORT_ID
                            )));
                    flight.setPlain(DAOFactory.getInstance().getPlainDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_PLAIN_ID
                            )));
                    flight.setDeparture(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_DEPARTURE_DATE));
                    flight.setArrival(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_ARRIVAL_DATE));
                    flight.setPrice(resultSet.getDouble(DbParameterName.REQ_FLIGHT_PRICE));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new FlightDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return flight;
    }

    @Override
    public List<Flight> getAll() throws FlightDAOException {
        List<Flight> flights = new ArrayList<Flight>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Flight flight = new Flight();

                    flight.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    flight.setFrom_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_FROM_AIRPORT_ID
                            )));
                    flight.setTo_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_TO_AIRPORT_ID
                            )));
                    flight.setPlain(DAOFactory.getInstance().getPlainDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_PLAIN_ID
                            )));
                    flight.setDeparture(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_DEPARTURE_DATE));
                    flight.setArrival(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_ARRIVAL_DATE));
                    flight.setPrice(resultSet.getDouble(DbParameterName.REQ_FLIGHT_PRICE));

                    flights.add(flight);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new FlightDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return flights;
    }

    @Override
    public void insert(Flight flight) throws FlightDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setInt(1, flight.getFrom_airport().getId());
            callableStatement.setInt(2, flight.getTo_airport().getId());
            callableStatement.setInt(3, flight.getPlain().getId());
//            callableStatement.setTimestamp(4, flight.getDeparture());
//            callableStatement.setTimestamp(5, flight.getArrival());
            callableStatement.setDouble(6, flight.getPrice());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    flight.setId(resultSet.getInt(DbParameterName.REQ_ID));

                    flight.setFrom_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_FROM_AIRPORT_ID
                            )));
                    flight.setTo_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_TO_AIRPORT_ID
                            )));
                    flight.setPlain(DAOFactory.getInstance().getPlainDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_PLAIN_ID
                            )));
                    flight.setDeparture(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_DEPARTURE_DATE));
                    flight.setArrival(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_ARRIVAL_DATE));

                    flight.setPrice(resultSet.getDouble(DbParameterName.REQ_FLIGHT_PRICE));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new FlightDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Flight flight) throws FlightDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, flight.getId());
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setInt(2, flight.getFrom_airport().getId());
            callableStatement.setInt(3, flight.getTo_airport().getId());
            callableStatement.setInt(4, flight.getPlain().getId());
//            callableStatement.setTimestamp(5, flight.getDeparture());
//            callableStatement.setTimestamp(6, flight.getArrival());
            callableStatement.setDouble(7, flight.getPrice());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    flight.setId(resultSet.getInt(DbParameterName.REQ_ID));

                    flight.setFrom_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_FROM_AIRPORT_ID
                            )));
                    flight.setTo_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_TO_AIRPORT_ID
                            )));
                    flight.setPlain(DAOFactory.getInstance().getPlainDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_PLAIN_ID
                            )));
                    flight.setDeparture(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_DEPARTURE_DATE));
                    flight.setArrival(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_ARRIVAL_DATE));
                    flight.setPrice(resultSet.getDouble(DbParameterName.REQ_FLIGHT_PRICE));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new FlightDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Flight flight) throws FlightDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, flight.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new FlightDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
