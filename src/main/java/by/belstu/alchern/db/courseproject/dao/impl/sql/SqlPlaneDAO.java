package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.PlaneDAO;
import by.belstu.alchern.db.courseproject.dao.exception.impl.PlaneDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Plane;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlPlaneDAO implements PlaneDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlPlaneDAO.class.getSimpleName());

    private static final String GET = "{call usp_planesSelect(?)}";
    private static final String GET_ALL = "{call usp_planesSelect(null)}";
    private static final String INSERT = "{call usp_planesInsert(?, ?, ?)}";
    private static final String UPDATE = "{call usp_planesUpdate(?, ?, ?, ?)}";
    private static final String DELETE = "{call usp_planesDelete(?)}";

    @Override
    public Plane get(int id) throws PlaneDAOException {
        Plane plane = null;
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
                    plane = new Plane();
                    plane.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plane.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plane.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plane.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                }
            }
        } catch (SQLException e) {
            throw new PlaneDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return plane;
    }

    @Override
    public List<Plane> getAll() throws PlaneDAOException {
        List<Plane> planes = new ArrayList<Plane>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Plane plane = new Plane();
                    plane.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plane.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plane.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plane.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                    planes.add(plane);
                }
            }
        } catch (SQLException e) {
            throw new PlaneDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return planes;
    }

    @Override
    public void insert(Plane plane) throws PlaneDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, plane.getNumber());
            callableStatement.setString(2, plane.getName());
            callableStatement.setInt(3, plane.getCapacity());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    plane.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plane.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plane.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plane.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                }
            }
        } catch (SQLException e) {
            throw new PlaneDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Plane plane) throws PlaneDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, plane.getId());
            callableStatement.setString(2, plane.getNumber());
            callableStatement.setString(3, plane.getName());
            callableStatement.setInt(4, plane.getCapacity());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    plane.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plane.setNumber(resultSet.getString(DbParameterName.REQ_PLAIN_NUMBER));
                    plane.setName(resultSet.getString(DbParameterName.REQ_PLAIN_NAME));
                    plane.setCapacity(resultSet.getInt(DbParameterName.REQ_PLAIN_CAPACITY));
                }
            }
        } catch (SQLException e) {
            throw new PlaneDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Plane plane) throws PlaneDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, plane.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new PlaneDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
