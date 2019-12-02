package by.belstu.alchern.db.courseproject.dao;

import by.belstu.alchern.db.courseproject.dao.exception.impl.FlightDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Flight;

import java.util.List;

public interface FlightDAO extends EntityDAO<Flight> {
    List<Flight> getByRange(int x1, int x2) throws FlightDAOException;

    List<Flight> getFlightByRequest(Flight flight) throws FlightDAOException;

    List<Flight> getOnTheWay() throws FlightDAOException;

    List<Flight> getDeparting() throws FlightDAOException;

    List<Flight> getOnTheWayOrderByPlane() throws FlightDAOException;

    List<Flight> getOnTheWayOrderByArrival() throws FlightDAOException;

    List<Flight> getOnTheWayOrderByDeparture() throws FlightDAOException;

    List<Flight> getOnTheWayOrderByFromAirport() throws FlightDAOException;

    List<Flight> getOnTheWayOrderByToAirport() throws FlightDAOException;

    List<Flight> getOnTheWayOrderByWillArrive() throws FlightDAOException;
}
