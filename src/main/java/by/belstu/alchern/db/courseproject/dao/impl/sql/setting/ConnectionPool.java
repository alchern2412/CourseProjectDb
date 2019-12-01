package by.belstu.alchern.db.courseproject.dao.impl.sql.setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }


    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConnectionQueue;

    //private ReentrantLock lock = new ReentrantLock();

    private String driverName;
    private int poolSize;
    private String url;
    private String user;
    private String password;

    private ConnectionPool() {
//        DbResourceManager dbResourceManager = DbResourceManager.getInstance();
//        this.driverName = dbResourceManager.getValue(DbParameter.DB_DRIVER);
//        this.user = dbResourceManager.getValue(DbParameter.DB_USER);
//        this.password = dbResourceManager.getValue(DbParameter.DB_PASSWORD);
//        this.url = dbResourceManager.getValue(DbParameter.DB_URL);
//        try {
//            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DbParameter.DB_POOL_SIZE));
//        } catch (NumberFormatException e) {
//            poolSize = 5;
//        }
        // -------
        driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        url = "jdbc:sqlserver://localhost;databaseName=CourseProjectDb;integratedSecurity=true";
        poolSize = 5;
        // -------
        initPoolData();


    }

    private void initPoolData() {
        try {
            Class.forName(driverName);

            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenAwayConnectionQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                //Connection connection = DriverManager.getConnection(url, user, password);
                Connection connection = DriverManager.getConnection(url);

                connectionQueue.add(connection);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // change!!!
        } catch (SQLException e) {
            e.printStackTrace();
            // change!!!
        }
    }

    public Connection takeConnection() {
        Connection connection;

        connection = connectionQueue.remove();
        givenAwayConnectionQueue.add(connection);

        return connection;
    }

    public void putBackConnection(Connection c) throws NullPointerException {
        if (c != null) {
            if (givenAwayConnectionQueue.remove(c)) {
                connectionQueue.add(c);
            } else {
                throw new NullPointerException("Connection not in the used");
            }
        }

    }

    public void closeConnections() {
        for (Connection connection : connectionQueue) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        for (Connection connection : givenAwayConnectionQueue) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }
}
