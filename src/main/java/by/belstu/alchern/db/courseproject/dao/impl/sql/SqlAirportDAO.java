package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.AirportDAO;
import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.AirportDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Airport;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlAirportDAO implements AirportDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlAirportDAO.class.getSimpleName());

    private static final String GET = "{call usp_airportsSelect(?)}";
    private static final String GET_ALL = "{call usp_airportsSelect(null)}";
    private static final String INSERT = "{call usp_airportsInsert(?)}";
    private static final String UPDATE = "{call usp_airportsUpdate(?, ?)}";
    private static final String DELETE = "{call usp_airportsDelete(?)}";

    @Override
    public Airport get(int id) throws AirportDAOException {
        Airport airport = null;
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
                    airport = new Airport();
                    airport.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    airport.setName(resultSet.getString(DbParameterName.REQ_AIRPORT_NAME));
                    airport.setCountry(DAOFactory.getInstance().getCountryDAO().get(
                            resultSet.getInt(DbParameterName.REQ_AIRPORT_COUNTRY_ID)
                    ));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new AirportDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return airport;
    }

    @Override
    public List<Airport> getAll() throws AirportDAOException {
        List<Airport> airports = new ArrayList<Airport>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Airport airport = new Airport();
                    airport.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    airport.setName(resultSet.getString(DbParameterName.REQ_AIRPORT_NAME));
                    airport.setCountry(DAOFactory.getInstance().getCountryDAO().get(
                            resultSet.getInt(DbParameterName.REQ_AIRPORT_COUNTRY_ID)
                    ));
                    airports.add(airport);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new AirportDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return airports;
    }

    @Override
    public void insert(Airport airport) throws AirportDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, airport.getName());
            callableStatement.setInt(2, airport.getCountry().getId());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    airport.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    airport.setName(resultSet.getString(DbParameterName.REQ_AIRPORT_NAME));
                    airport.setCountry(DAOFactory.getInstance().getCountryDAO().get(
                            resultSet.getInt(DbParameterName.REQ_AIRPORT_COUNTRY_ID)
                    ));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new AirportDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Airport airport) throws AirportDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, airport.getId());
            callableStatement.setString(2, airport.getName());
            callableStatement.setInt(3, airport.getCountry().getId());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    airport.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    airport.setName(resultSet.getString(DbParameterName.REQ_AIRPORT_NAME));
                    airport.setCountry(DAOFactory.getInstance().getCountryDAO().get(
                            resultSet.getInt(DbParameterName.REQ_AIRPORT_COUNTRY_ID)
                    ));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new AirportDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Airport airport) throws AirportDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, airport.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new AirportDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
