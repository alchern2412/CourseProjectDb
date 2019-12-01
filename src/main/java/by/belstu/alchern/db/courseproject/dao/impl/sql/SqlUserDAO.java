package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.UserDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.UserDAOException;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbParameterName;
import by.belstu.alchern.db.courseproject.model.impl.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDAO implements UserDAO {
    // crud
    private static final String GET = "{call usp_usersSelect(?)}";
    private static final String GET_ALL = "{call usp_usersSelect(null)}";
    private static final String INSERT = "{call usp_usersInsert(?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String UPDATE = "{call usp_usersUpdate(?, ?, ?, ?, ?, ?, ?, ?, ?)  }";
    private static final String DELETE = "{call usp_usersDelete(?)}";

    // services
    private static final String LOGIN = "{call usp_usersLogin(?, ?)}";



    @Override
    public User get(int id) throws UserDAOException {
        User user = null;
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
                    user = new User();
                    user.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    user.setAddress(resultSet.getString(DbParameterName.REQ_USER_ADDRESS));
                    user.setDocumentNumber(resultSet.getString(DbParameterName.REQ_USER_DOCUMENT_NUMBER));
                    user.setFirstName(resultSet.getString(DbParameterName.REQ_USER_FIRST_NAME));
                    user.setLastName(resultSet.getString(DbParameterName.REQ_USER_LAST_NAME));
                    user.setLogin(resultSet.getString(DbParameterName.REQ_USER_LOGIN));
                    user.setPassword(resultSet.getString(DbParameterName.REQ_USER_PASSWORD));
                    user.setRole(DAOFactory.getInstance().getRoleDAO().get(resultSet.getInt(DbParameterName.REQ_USER_ROLE_ID)));
                    user.setTel(resultSet.getString(DbParameterName.REQ_USER_TEL));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new UserDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return user;
    }

    @Override
    public List<User> getAll() throws UserDAOException {
        List<User> users = new ArrayList<User>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(GET_ALL);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    user.setAddress(resultSet.getString(DbParameterName.REQ_USER_ADDRESS));
                    user.setDocumentNumber(resultSet.getString(DbParameterName.REQ_USER_DOCUMENT_NUMBER));
                    user.setFirstName(resultSet.getString(DbParameterName.REQ_USER_FIRST_NAME));
                    user.setLastName(resultSet.getString(DbParameterName.REQ_USER_LAST_NAME));
                    user.setLogin(resultSet.getString(DbParameterName.REQ_USER_LOGIN));
                    user.setPassword(resultSet.getString(DbParameterName.REQ_USER_PASSWORD));
                    user.setRole(DAOFactory.getInstance().getRoleDAO().get(resultSet.getInt(DbParameterName.REQ_USER_ROLE_ID)));
                    user.setTel(resultSet.getString(DbParameterName.REQ_USER_TEL));

                    users.add(user);
                }
            }
        } catch (SQLException | DAOException e) {
            throw new UserDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return users;
    }

    @Override
    public void insert(User user) throws UserDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(INSERT);
            callableStatement.setString(1, user.getLogin());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getFirstName());
            callableStatement.setString(4, user.getLastName());
            callableStatement.setString(5, user.getDocumentNumber());
            callableStatement.setString(6, user.getTel());
            callableStatement.setString(7, user.getAddress());
            callableStatement.setInt(8, user.getRole().getId());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    user.setAddress(resultSet.getString(DbParameterName.REQ_USER_ADDRESS));
                    user.setDocumentNumber(resultSet.getString(DbParameterName.REQ_USER_DOCUMENT_NUMBER));
                    user.setFirstName(resultSet.getString(DbParameterName.REQ_USER_FIRST_NAME));
                    user.setLastName(resultSet.getString(DbParameterName.REQ_USER_LAST_NAME));
                    user.setLogin(resultSet.getString(DbParameterName.REQ_USER_LOGIN));
                    user.setPassword(resultSet.getString(DbParameterName.REQ_USER_PASSWORD));
                    user.setRole(DAOFactory.getInstance().getRoleDAO().get(resultSet.getInt(DbParameterName.REQ_USER_ROLE_ID)));
                    user.setTel(resultSet.getString(DbParameterName.REQ_USER_TEL));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new UserDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void update(User user) throws UserDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(UPDATE);
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, user.getLogin());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setString(4, user.getFirstName());
            callableStatement.setString(5, user.getLastName());
            callableStatement.setString(6, user.getDocumentNumber());
            callableStatement.setString(7, user.getTel());
            callableStatement.setString(8, user.getAddress());
            callableStatement.setInt(9, user.getRole().getId());
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    user.setAddress(resultSet.getString(DbParameterName.REQ_USER_ADDRESS));
                    user.setDocumentNumber(resultSet.getString(DbParameterName.REQ_USER_DOCUMENT_NUMBER));
                    user.setFirstName(resultSet.getString(DbParameterName.REQ_USER_FIRST_NAME));
                    user.setLastName(resultSet.getString(DbParameterName.REQ_USER_LAST_NAME));
                    user.setLogin(resultSet.getString(DbParameterName.REQ_USER_LOGIN));
                    user.setPassword(resultSet.getString(DbParameterName.REQ_USER_PASSWORD));
                    user.setRole(DAOFactory.getInstance().getRoleDAO().get(resultSet.getInt(DbParameterName.REQ_USER_ROLE_ID)));
                    user.setTel(resultSet.getString(DbParameterName.REQ_USER_TEL));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new UserDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }
    }

    @Override
    public void delete(User user) throws UserDAOException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(DELETE);
            callableStatement.setInt(1, user.getId());
            callableStatement.execute();

        } catch (SQLException e) {
            throw new UserDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

    }

    @Override
    public User login(String login, String password) throws UserDAOException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(LOGIN);
            callableStatement.setString(1, login);
            callableStatement.setString(2, password);
            boolean hadResults = callableStatement.execute();

            if (hadResults) {
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(DbParameterName.REQ_ID));
                    user.setAddress(resultSet.getString(DbParameterName.REQ_USER_ADDRESS));
                    user.setDocumentNumber(resultSet.getString(DbParameterName.REQ_USER_DOCUMENT_NUMBER));
                    user.setFirstName(resultSet.getString(DbParameterName.REQ_USER_FIRST_NAME));
                    user.setLastName(resultSet.getString(DbParameterName.REQ_USER_LAST_NAME));
                    user.setLogin(resultSet.getString(DbParameterName.REQ_USER_LOGIN));
                    user.setPassword(resultSet.getString(DbParameterName.REQ_USER_PASSWORD));
                    user.setRole(DAOFactory.getInstance().getRoleDAO().get(resultSet.getInt(DbParameterName.REQ_USER_ROLE_ID)));
                    user.setTel(resultSet.getString(DbParameterName.REQ_USER_TEL));
                }
            }
        } catch (SQLException | DAOException e) {
            throw new UserDAOException(e);
        } finally {
            SqlDAO.closeResultSet(resultSet);
            SqlDAO.closeCallableStatement(callableStatement);
            SqlDAO.putBackConnection(connection);
        }

        return user;
    }
}
