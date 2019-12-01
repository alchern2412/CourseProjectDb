package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.PositionDAO;
import by.belstu.alchern.db.courseproject.dao.exception.impl.PositionDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Position;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlPositionDAO implements PositionDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlPositionDAO.class.getSimpleName());

    private static final String GET = "{call usp_positionsSelect(?)}";
    private static final String GET_ALL = "{call usp_positionsSelect(null)}";
    private static final String INSERT = "{call usp_positionsInsert(?)}";
    private static final String UPDATE = "{call usp_positionsUpdate(?, ?)}";
    private static final String DELETE = "{call usp_positionsDelete(?)}";

    @Override
    public Position get(int id) throws PositionDAOException {
        Position position = null;
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
                    position = new Position();
                    position.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    position.setPosition(resultSet.getString(DbParameterName.REQ_POSITION_POSITION));
                }
            }
        } catch (SQLException e) {
            throw new PositionDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return position;
    }

    @Override
    public List<Position> getAll() throws PositionDAOException {
        List<Position> positions = new ArrayList<Position>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Position position = new Position();
                    position.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    position.setPosition(resultSet.getString(DbParameterName.REQ_POSITION_POSITION));
                    positions.add(position);
                }
            }
        } catch (SQLException e) {
            throw new PositionDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return positions;
    }

    @Override
    public void insert(Position position) throws PositionDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, position.getPosition());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    position.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    position.setPosition(resultSet.getString(DbParameterName.REQ_ROLE_ROLE));
                }
            }
        } catch (SQLException e) {
            throw new PositionDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Position position) throws PositionDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, position.getId());
            callableStatement.setString(2, position.getPosition());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    position.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    position.setPosition(resultSet.getString(DbParameterName.REQ_ROLE_ROLE));
                }
            }
        } catch (SQLException e) {
            throw new PositionDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Position position) throws PositionDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, position.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new PositionDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
