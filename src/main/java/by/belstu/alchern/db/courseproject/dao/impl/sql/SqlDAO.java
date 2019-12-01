package by.belstu.alchern.db.courseproject.dao.impl.sql;

import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;
import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.DbResourceManager;
import org.apache.log4j.Logger;
import java.sql.*;

public class SqlDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlDAO.class.getSimpleName());
    static {
        try {
            Class.forName(DbResourceManager.getInstance().getValue("db.driver"));
        } catch (ClassNotFoundException e) {
            LOGGER.error("Driver: " + e.getMessage());
        }
    }

    static void putBackConnection(Connection connection) {
        if (connection != null) {                   // return connection
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

    static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error("PreperedStatement: " + e.getMessage());
            }
        }
    }

    static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Statement: " + e.getMessage());
            }
        }
    }

    static void closeCallableStatement(CallableStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Statement: " + e.getMessage());
            }
        }
    }

    static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("ResultSet: " + e.getMessage());
            }
        }
    }
}
