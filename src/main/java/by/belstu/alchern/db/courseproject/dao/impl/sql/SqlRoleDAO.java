package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.RoleDAO;
import by.belstu.alchern.db.courseproject.dao.exception.impl.RoleDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlRoleDAO extends SqlDAO implements RoleDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlRoleDAO.class.getSimpleName());

    private static final String GET = "{call usp_rolesSelect(?)}";
    private static final String GET_ALL = "{call usp_rolesSelect(null)}";
    private static final String INSERT = "{call usp_rolesInsert(?)";
    private static final String UPDATE = "{call usp_rolesUpdate(?, ?)  ";
    private static final String DELETE = "{call usp_rolesDelete(?)}";

    @Override
    public Role get(int id) throws RoleDAOException {
        Role role = null;
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
                    role = new Role();
                    role.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    role.setRole(resultSet.getString(DbParameterName.REQ_ROLE_ROLE));
                }
            }
        } catch (SQLException e) {
            throw new RoleDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return role;
    }

    @Override
    public List<Role> getAll() throws RoleDAOException {
        List<Role> roles = new ArrayList<Role>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    role.setRole(resultSet.getString(DbParameterName.REQ_ROLE_ROLE));
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            throw new RoleDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return roles;
    }

    @Override
    public void insert(Role role) throws RoleDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, role.getRole());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    role.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    role.setRole(resultSet.getString(DbParameterName.REQ_ROLE_ROLE));
                }
            }
        } catch (SQLException e) {
            throw new RoleDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(Role role) throws RoleDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, role.getId());
            callableStatement.setString(2, role.getRole());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    role.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    role.setRole(resultSet.getString(DbParameterName.REQ_ROLE_ROLE));
                }
            }
        } catch (SQLException e) {
            throw new RoleDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(Role role) throws RoleDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, role.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new RoleDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }
}
