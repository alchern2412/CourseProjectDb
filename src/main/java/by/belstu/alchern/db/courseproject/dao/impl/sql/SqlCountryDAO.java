package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.CountryDAO;
import by.belstu.alchern.db.courseproject.dao.exception.impl.CountryDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Country;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlCountryDAO implements CountryDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlCountryDAO.class.getSimpleName());

    private static final String GET = "{call usp_countriesSelect(?)}";
    private static final String GET_ALL = "{call usp_countriesSelect(null)}";
    private static final String INSERT = "{call usp_countriesInsert(?)}";
    private static final String UPDATE = "{call usp_countriesUpdate(?, ?)}";
    private static final String DELETE = "{call usp_countriesDelete(?)}";

    // services
    private static final String EXPORT_XML = "{call usp_countriesExportXml}";
    private static final String IMPORT_XML = "{call usp_countriesImportXml(?)}";


    @Override
    public Country get(int id) throws CountryDAOException {
        Country country = null;
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
                    country = new Country();
                    country.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    country.setName(resultSet.getString(DbParameterName.REQ_COUNTRY_NAME));
                }
            }
        } catch (SQLException e) {
            throw new CountryDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return country;
    }

    @Override
    public List<Country> getAll() throws CountryDAOException {
        List<Country> countries = new ArrayList<Country>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Country country = new Country();
                    country.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    country.setName(resultSet.getString(DbParameterName.REQ_COUNTRY_NAME));
                    countries.add(country);
                }
            }
        } catch (SQLException e) {
            throw new CountryDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return countries;
    }

    @Override
    public void insert(Country country) throws CountryDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, country.getName());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    country.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    country.setName(resultSet.getString(DbParameterName.REQ_COUNTRY_NAME));
                }
            }
        } catch (SQLException e) {
            throw new CountryDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Country country) throws CountryDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, country.getId());
            callableStatement.setString(2, country.getName());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    country.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    country.setName(resultSet.getString(DbParameterName.REQ_COUNTRY_NAME));
                }
            }
        } catch (SQLException e) {
            throw new CountryDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Country country) throws CountryDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, country.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new CountryDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }

    @Override
    public String exportXml() throws CountryDAOException {
        String xmlText = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(EXPORT_XML);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    xmlText = resultSet.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new CountryDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return xmlText;
    }

    @Override
    public void importXml(String path) throws CountryDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(IMPORT_XML);
            callableStatement.setString(1, path);
            boolean hadResults = callableStatement.execute();
        } catch (SQLException e) {
            throw new CountryDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }
}
