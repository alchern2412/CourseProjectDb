package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.PlainDAO;
import by.belstu.alchern.db.courseproject.dao.exception.impl.PlainDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameter;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Plain;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlPlainDAO implements PlainDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlPlainDAO.class.getSimpleName());

    private static final String GET = "{call usp_plainsSelect(?)}";
    private static final String GET_ALL = "{call usp_plainsSelect(null)}";
    private static final String INSERT = "{call usp_plainsInsert(?, ?, ?)}";
    private static final String UPDATE = "{call usp_plainsUpdate(?, ?, ?, ?)}";
    private static final String DELETE = "{call usp_plainsDelete(?)}";

    @Override
    public Plain get(int id) throws PlainDAOException {
        Plain plain = null;
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
                    plain = new Plain();
                    plain.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plain.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plain.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plain.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                }
            }
        } catch (SQLException e) {
            throw new PlainDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return plain;
    }

    @Override
    public List<Plain> getAll() throws PlainDAOException {
        List<Plain> plains = new ArrayList<Plain>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Plain plain = new Plain();
                    plain.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plain.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plain.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plain.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                    plains.add(plain);
                }
            }
        } catch (SQLException e) {
            throw new PlainDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return plains;
    }

    @Override
    public void insert(Plain plain) throws PlainDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, plain.getNumber());
            callableStatement.setString(2, plain.getName());
            callableStatement.setInt(3, plain.getCapacity());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    plain.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plain.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plain.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plain.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                }
            }
        } catch (SQLException e) {
            throw new PlainDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Plain plain) throws PlainDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, plain.getId());
            callableStatement.setString(2, plain.getNumber());
            callableStatement.setString(3, plain.getName());
            callableStatement.setInt(4, plain.getCapacity());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    plain.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plain.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plain.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plain.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                }
            }
        } catch (SQLException e) {
            throw new PlainDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Plain plain) throws PlainDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, plain.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new PlainDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
