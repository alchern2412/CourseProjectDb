package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.PlaneStuffDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.PlaneStaffDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.PlaneStaff;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlPlaneStaffDAO implements PlaneStuffDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlPlaneStaffDAO.class.getSimpleName());

    private static final String GET = "{call usp_plane_StaffSelect(?)}";
    private static final String GET_ALL = "{call usp_plane_StaffSelect(null)}";
    private static final String INSERT = "{call usp_plane_StaffInsert(?, ?)}";
    private static final String UPDATE = "{call usp_plane_StaffUpdate(?, ?, ?)} ";
    private static final String DELETE = "{call usp_plane_StaffDelete(?)}";

    @Override
    public PlaneStaff get(int id) throws PlaneStaffDAOException {
        PlaneStaff planeStaff = null;
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
                    planeStaff = new PlaneStaff();
                    planeStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    planeStaff.setPlane(DAOFactory.getInstance().getPlaneDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    planeStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));

                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlaneStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return planeStaff;
    }

    @Override
    public List<PlaneStaff> getAll() throws PlaneStaffDAOException {
        List<PlaneStaff> planeStaffList = new ArrayList<PlaneStaff>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    PlaneStaff planeStaff = new PlaneStaff();
                    planeStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    planeStaff.setPlane(DAOFactory.getInstance().getPlaneDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    planeStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));
                    planeStaffList.add(planeStaff);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlaneStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return planeStaffList;
    }

    @Override
    public void insert(PlaneStaff planeStaff) throws PlaneStaffDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setInt(1, planeStaff.getPlane().getId());
            callableStatement.setInt(2, planeStaff.getWorker().getId());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    planeStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    planeStaff.setPlane(DAOFactory.getInstance().getPlaneDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    planeStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlaneStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(PlaneStaff planeStaff) throws PlaneStaffDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, planeStaff.getId());
            callableStatement.setInt(2, planeStaff.getPlane().getId());
            callableStatement.setInt(3, planeStaff.getWorker().getId());

            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    planeStaff.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    planeStaff.setPlane(DAOFactory.getInstance().getPlaneDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_PLAIN_ID)));
                    planeStaff.setWorker(DAOFactory.getInstance().getWorkerDAO().get(resultSet.getInt(DbParameterName.REQ_PLAIN_STAFF_WORKER_ID)));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new PlaneStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(PlaneStaff planeStaff) throws PlaneStaffDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, planeStaff.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new PlaneStaffDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
