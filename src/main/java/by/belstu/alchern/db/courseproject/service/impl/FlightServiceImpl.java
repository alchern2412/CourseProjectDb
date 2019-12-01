package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.impl.FlightDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Flight;
import by.belstu.alchern.db.courseproject.service.FlightService;
import by.belstu.alchern.db.courseproject.service.exception.FlightServiceException;

import java.util.List;

public class FlightServiceImpl implements FlightService {
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
}
