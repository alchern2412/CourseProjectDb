package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.service.impl.*;

public class ServiceProvider {
    private ServiceProvider() {
    }

    private static final ServiceProvider instance = new ServiceProvider();

    public static ServiceProvider getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();
    private final AirportService airportService = new AirportServiceImpl();
    private final CountryService countryService = new CountryServiceImpl();
    private final FlightService flightService = new FlightServiceImpl();
    private final PlaneService planeService = new PlaneServiceImpl();
    private final PlainStuffService plainStuffService = new PlainStuffServiceImpl();
    private final PositionService positionService = new PositionServiceImpl();
    private final RoleService roleService = new RoleServiceImpl();
    private final TicketService ticketService = new TicketServiceImpl();
    private final WorkerService workerService = new WorkerServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public AirportService getAirportService() {
        return airportService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public FlightService getFlightService() {
        return flightService;
    }

    public PlaneService getPlaneService() {
        return planeService;
    }

    public PlainStuffService getPlainStuffService() {
        return plainStuffService;
    }

    public PositionService getPositionService() {
        return positionService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public WorkerService getWorkerService() {
        return workerService;
    }
}
