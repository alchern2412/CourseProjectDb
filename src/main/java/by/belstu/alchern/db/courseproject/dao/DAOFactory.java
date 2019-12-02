package by.belstu.alchern.db.courseproject.dao;

import by.belstu.alchern.db.courseproject.dao.impl.sql.*;

public class DAOFactory {
    private DAOFactory() {}

    private final UserDAO userDAO = new SqlUserDAO();
    private final AirportDAO airportDAO = new SqlAirportDAO();
    private final CountryDAO countryDAO = new SqlCountryDAO();
    private final FlightDAO flightDAO = new SqlFlightDAO();
    private final PlaneDAO planeDAO = new SqlPlaneDAO();
    private final PlaneStuffDAO planeStuffDAO = new SqlPlaneStaffDAO();
    private final PositionDAO positionDAO = new SqlPositionDAO();
    private final RoleDAO roleDAO = new SqlRoleDAO();
    private final TicketDAO ticketDAO = new SqlTicketDAO();
    private final WorkerDAO workerDAO = new SqlWorkerDAO();
    // other DAO-s

    private static final DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public AirportDAO getAirportDAO() {
        return airportDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public FlightDAO getFlightDAO() {
        return flightDAO;
    }

    public PlaneDAO getPlaneDAO() {
        return planeDAO;
    }

    public PlaneStuffDAO getPlaneStuffDAO() {
        return planeStuffDAO;
    }

    public PositionDAO getPositionDAO() {
        return positionDAO;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public WorkerDAO getWorkerDAO() {
        return workerDAO;
    }
}
