package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.Flight;
import by.belstu.alchern.db.courseproject.service.exception.FlightServiceException;

import java.util.List;

public interface FlightService {
    List<Flight> getByPageNum(int pageNum) throws FlightServiceException;
}
