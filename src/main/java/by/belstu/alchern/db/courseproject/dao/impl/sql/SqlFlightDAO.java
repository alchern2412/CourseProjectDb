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
import java.text.SimpleDateFormat;
import java.util.*;

public class SqlFlightDAO implements FlightDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlFlightDAO.class.getSimpleName());

    private static final String GET = "{call usp_flightsSelect(?)}";
    private static final String GET_ALL = "{call usp_flightsSelect(null)}";
    private static final String INSERT = "{call usp_flightsInsert(?, ?, ?, ?, ?, ?)}";
    private static final String UPDATE = "{call usp_flightsUpdate(?, ?, ?, ?, ?, ?, ?)}";
    private static final String DELETE = "{call usp_flightsDelete(?)}";

    // services
    private static final String GET_BY_RANGE = "{call usp_flightsSelectByRange(?, ?)}";
    private static final String GET_BY_REQUEST = "{call usp_flightsSelectByRequest(?, ?, ?, ?)}";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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

    @Override
    public List<Flight> getByRange(int x1, int x2) throws FlightDAOException {
        List<Flight> flights = new ArrayList<Flight>();
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
    public List<Flight> getFlightByRequest(Flight flight) throws FlightDAOException {
        List<Flight> flights = new ArrayList<Flight>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_BY_REQUEST);
            callableStatement.setString(1, sdf.format(setTime(flight.getDeparture(), 0,
                    0, 0, 0)));
            callableStatement.setString(2, sdf.format(setTime(addDays(flight.getDeparture(), 1),
                    0, 0, 0, 0)));
            callableStatement.setInt(3, flight.getFrom_airport().getId());
            callableStatement.setInt(4, flight.getTo_airport().getId());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Flight findedFlight = new Flight();

                    findedFlight.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    findedFlight.setFrom_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_FROM_AIRPORT_ID
                            )));
                    findedFlight.setTo_airport(DAOFactory.getInstance().getAirportDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_TO_AIRPORT_ID
                            )));
                    findedFlight.setPlain(DAOFactory.getInstance().getPlainDAO().get(
                            resultSet.getInt(DbParameterName.REQ_FLIGHT_PLAIN_ID
                            )));
                    findedFlight.setDeparture(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_DEPARTURE_DATE));
                    findedFlight.setArrival(resultSet.getTimestamp(DbParameterName.REQ_FLIGHT_ARRIVAL_DATE));
                    findedFlight.setPrice(resultSet.getDouble(DbParameterName.REQ_FLIGHT_PRICE));

                    flights.add(findedFlight);
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




    /**
     * The method returns date with the time value.
     *
     * @param date Old Date
     * @param hourOfDay Hours value
     * @param minute Minutes value
     * @param second Seconds value
     * @param ms    Milliseconds value
     * @return  New Date
     */
    private static Date setTime(final Date date, final int hourOfDay, final int minute, final int second, final int ms) {
        final GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, hourOfDay);
        gc.set(Calendar.MINUTE, minute);
        gc.set(Calendar.SECOND, second);
        gc.set(Calendar.MILLISECOND, ms);
        return gc.getTime();
    }

    /**
     * This method add count of days to Date object
     * @param date  Date
     * @param days  Days count
     * @return New date
     */
    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
