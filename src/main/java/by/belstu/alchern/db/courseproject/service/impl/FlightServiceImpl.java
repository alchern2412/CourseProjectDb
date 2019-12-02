package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.FlightDAO;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.FlightDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Flight;
import by.belstu.alchern.db.courseproject.service.FlightService;
import by.belstu.alchern.db.courseproject.service.exception.FlightServiceException;
import by.belstu.alchern.db.courseproject.view.dto.FlightDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class FlightServiceImpl implements FlightService {

    private static final String dateFormat = "MM/dd/yyyy hh:mm a";

    @Override
    public List<Flight> getByPageNum(int pageNum) throws FlightServiceException {
        List<Flight> flights = null;
        try {
            flights = DAOFactory.getInstance().getFlightDAO().getByRange((pageNum-1) * 5, (pageNum-1) * 5 + 10 );
        } catch (FlightDAOException e) {
            throw new FlightServiceException(e);
        }
        return  flights;
    }

    @Override
    public List<Flight> getFlightsByRequest(FlightDTO flightDTO) throws FlightServiceException {
        // Flight validation

        Flight flight = new Flight();
        DAOFactory factory = DAOFactory.getInstance();
        try {
            flight.setFrom_airport(factory.getAirportDAO().get(Integer.parseInt(flightDTO.getFromAirport())));
            flight.setTo_airport(factory.getAirportDAO().get(Integer.parseInt(flightDTO.getToAirport())));
        } catch (DAOException e) {
            throw new FlightServiceException(e);

        }
        try {
            flight.setDeparture(new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(flightDTO.getDeparture()));
        } catch (ParseException e) {
            throw new FlightServiceException("Invalid data format", e);
        }
        //flight.setPassengers(1);    // add one passengers (1 ticket = 1 passenger)

        List<Flight> flights = null;
        try {
            flights = DAOFactory.getInstance().getFlightDAO().getFlightByRequest(flight);
        } catch (FlightDAOException e) {
            throw new FlightServiceException(e);
        }
        return flights;
    }

    @Override
    public Flight create(FlightDTO flightDTO) throws FlightServiceException {
        // Flight validation

        Flight flight = new Flight();
        DAOFactory factory = DAOFactory.getInstance();
        try {
            flight.setFrom_airport(factory.getAirportDAO().get(Integer.parseInt(flightDTO.getFromAirport())));
            flight.setTo_airport(factory.getAirportDAO().get(Integer.parseInt(flightDTO.getToAirport())));
            flight.setDeparture(new SimpleDateFormat(dateFormat).parse(flightDTO.getDeparture()));
            flight.setArrival(new SimpleDateFormat(dateFormat).parse(flightDTO.getArrival()));
            flight.setPlane(factory.getPlaneDAO().get(Integer.parseInt(flightDTO.getPlane())));
            flight.setPrice(Double.parseDouble(flightDTO.getPrice()));
            DAOFactory.getInstance().getFlightDAO().insert(flight);
        } catch (DAOException | ParseException e) {
            throw new FlightServiceException(e);
        }

        return flight;
    }

    @Override
    public Flight get(int flightId) throws FlightServiceException {
        try {
            return DAOFactory.getInstance().getFlightDAO().get(flightId);
        } catch (DAOException e) {
            throw new FlightServiceException(e);
        }
    }

    @Override
    public Flight edit(Flight flight, FlightDTO flightDTO) throws FlightServiceException {
        // Flight validation

        DAOFactory factory = DAOFactory.getInstance();
        try {
            flight.setFrom_airport(factory.getAirportDAO().get(Integer.parseInt(flightDTO.getFromAirport())));
            flight.setTo_airport(factory.getAirportDAO().get(Integer.parseInt(flightDTO.getToAirport())));
            flight.setDeparture(new SimpleDateFormat(dateFormat).parse(flightDTO.getDeparture()));
            flight.setArrival(new SimpleDateFormat(dateFormat).parse(flightDTO.getArrival()));
            flight.setPlane(factory.getPlaneDAO().get(Integer.parseInt(flightDTO.getPlane())));
            flight.setPrice(Double.parseDouble(flightDTO.getPrice()));

            DAOFactory.getInstance().getFlightDAO().update(flight);
        } catch (DAOException | ParseException e) {
            throw new FlightServiceException(e);
        }

        return flight;
    }

    @Override
    public List<Flight> getAll() throws FlightServiceException {
        try {
            return DAOFactory.getInstance().getFlightDAO().getAll();
        } catch (DAOException e) {
            throw new FlightServiceException(e);
        }
    }

    @Override
    public List<Flight> getOnTheWay(String orderBy) throws FlightServiceException {
        final String PLANE = "plane";
        final String ARRIVAL = "arrival";
        final String DEPARTURE = "departure";
        final String FROM_AIRPORT = "from-airport";
        final String TO_AIRPORT = "to-airport";
        final String WILL_ARRIVE = "will-arrive";

        List<Flight> flights = null;

        try {
            FlightDAO flightDAO = DAOFactory.getInstance().getFlightDAO();
            switch (orderBy) {
                case PLANE:
                    flights = flightDAO.getOnTheWayOrderByPlane();
                    break;
                case ARRIVAL:
                    flights = flightDAO.getOnTheWayOrderByArrival();
                    break;
                case DEPARTURE:
                    flights = flightDAO.getOnTheWayOrderByDeparture();
                    break;
                case FROM_AIRPORT:
                    flights = flightDAO.getOnTheWayOrderByFromAirport();
                    break;
                case TO_AIRPORT:
                    flights = flightDAO.getOnTheWayOrderByToAirport();
                    break;
                case WILL_ARRIVE:
                    flights = flightDAO.getOnTheWayOrderByWillArrive();
                    break;
            }

            return flights;
        } catch (DAOException e) {
            throw new FlightServiceException(e);
        }
    }

    @Override
    public List<Flight> getDeparting() throws FlightServiceException {
        try {
            return DAOFactory.getInstance().getFlightDAO().getDeparting();
        } catch (DAOException e) {
            throw new FlightServiceException(e);
        }
    }
}
