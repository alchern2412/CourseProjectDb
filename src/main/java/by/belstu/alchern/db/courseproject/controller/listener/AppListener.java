package by.belstu.alchern.db.courseproject.controller.listener;

import by.belstu.alchern.db.courseproject.dao.impl.sql.setting.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closeConnections();    // close all connections in ConnectionPool
    }
}
