package by.belstu.alchern.db.courseproject.dao;

import by.belstu.alchern.db.courseproject.dao.exception.impl.FlightDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Flight;

import java.util.List;

public interface FlightDAO extends EntityDAO<Flight> {
    List<Flight> getByRange(int x1, int x2) throws FlightDAOException;

    List<Flight> getFlightByRequest(Flight flight) throws FlightDAOException;
}
