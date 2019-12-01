package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.WorkerDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.WorkerDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Worker;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlWorkerDAO implements WorkerDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlWorkerDAO.class.getSimpleName());

    private static final String GET = "{call usp_workersSelect(?)}";
    private static final String GET_ALL = "{call usp_workersSelect(null)}";
    private static final String INSERT = "{call usp_workersInsert(?, ?, ?)}";
    private static final String UPDATE = "{call usp_workersUpdate(?, ?, ?, ?) } ";
    private static final String DELETE = "{call usp_workersDelete(?)}";

    @Override
    public Worker get(int id) throws WorkerDAOException {
        Worker worker = null;
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
                    worker = new Worker();
                    worker.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    worker.setFirstName(resultSet.getString(DbParameterName.REQ_WORKER_FIRST_NAME));
                    worker.setLastName(resultSet.getString(DbParameterName.REQ_WORKER_LAST_NAME));
                    worker.setPosition(DAOFactory.getInstance().getPositionDAO().get(resultSet.getInt(DbParameterName.REQ_WORKER_POSITION_ID)));

                }
            }
        } catch (SQLException | DAOException e) {
            throw new WorkerDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return worker;
    }

    @Override
    public List<Worker> getAll() throws WorkerDAOException {
        List<Worker> workers = new ArrayList<Worker>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Worker worker = new Worker();
                    worker.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    worker.setFirstName(resultSet.getString(DbParameterName.REQ_WORKER_FIRST_NAME));
                    worker.setLastName(resultSet.getString(DbParameterName.REQ_WORKER_LAST_NAME));
                    worker.setPosition(DAOFactory.getInstance().getPositionDAO().get(resultSet.getInt(DbParameterName.REQ_WORKER_POSITION_ID)));
                    workers.add(worker);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new WorkerDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return workers;
    }

    @Override
    public void insert(Worker worker) throws WorkerDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, worker.getFirstName());
            callableStatement.setString(2, worker.getLastName());
            callableStatement.setInt(3, worker.getPosition().getId());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    worker.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    worker.setFirstName(resultSet.getString(DbParameterName.REQ_WORKER_FIRST_NAME));
                    worker.setLastName(resultSet.getString(DbParameterName.REQ_WORKER_LAST_NAME));
                    worker.setPosition(DAOFactory.getInstance().getPositionDAO().get(resultSet.getInt(DbParameterName.REQ_WORKER_POSITION_ID)));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new WorkerDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Worker worker) throws WorkerDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, worker.getId());
            callableStatement.setString(2, worker.getFirstName());
            callableStatement.setString(3, worker.getLastName());
            callableStatement.setInt(4, worker.getPosition().getId());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    worker.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    worker.setFirstName(resultSet.getString(DbParameterName.REQ_WORKER_FIRST_NAME));
                    worker.setLastName(resultSet.getString(DbParameterName.REQ_WORKER_LAST_NAME));
                    worker.setPosition(DAOFactory.getInstance().getPositionDAO().get(resultSet.getInt(DbParameterName.REQ_WORKER_POSITION_ID)));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new WorkerDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Worker worker) throws WorkerDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, worker.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new WorkerDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
