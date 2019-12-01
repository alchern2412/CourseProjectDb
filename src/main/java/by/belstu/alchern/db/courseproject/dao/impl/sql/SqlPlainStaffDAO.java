package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.PlainStuffDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.PlainStaffDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.PlainStaff;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlPlainStaffDAO implements PlainStuffDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlPlainStaffDAO.class.getSimpleName());

    private static final String GET = "{call usp_plain_StaffSelect(?)}";
    private static final String GET_ALL = "{call usp_plain_StaffSelect(null)}";
    private static final String INSERT = "{call usp_plain_StaffInsert(?, ?)}";
    private static final String UPDATE = "{call usp_plain_StaffUpdate(?, ?, ?)} ";
    private static final String DELETE = "{call usp_plain_StaffDelete(?)}";

    @Override
    public PlainStaff get(int id) throws PlainStaffDAOException {
        PlainStaff plainStaff = null;
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
                    plainStaff = new PlainStaff();
                    plainStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plainStaff.setPlain(DAOFactory.getInstance().getPlainDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    plainStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));

                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlainStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return plainStaff;
    }

    @Override
    public List<PlainStaff> getAll() throws PlainStaffDAOException {
        List<PlainStaff> plainStaffList = new ArrayList<PlainStaff>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    PlainStaff plainStaff = new PlainStaff();
                    plainStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plainStaff.setPlain(DAOFactory.getInstance().getPlainDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    plainStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));
                    plainStaffList.add(plainStaff);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlainStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return plainStaffList;
    }

    @Override
    public void insert(PlainStaff plainStaff) throws PlainStaffDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setInt(1, plainStaff.getPlain().getId());
            callableStatement.setInt(2, plainStaff.getWorker().getId());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    plainStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plainStaff.setPlain(DAOFactory.getInstance().getPlainDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    plainStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlainStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(PlainStaff plainStaff) throws PlainStaffDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, plainStaff.getId());
            callableStatement.setInt(2, plainStaff.getPlain().getId());
            callableStatement.setInt(3, plainStaff.getWorker().getId());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    plainStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    plainStaff.setPlain(DAOFactory.getInstance().getPlainDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    plainStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlainStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(PlainStaff plainStaff) throws PlainStaffDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, plainStaff.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new PlainStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
