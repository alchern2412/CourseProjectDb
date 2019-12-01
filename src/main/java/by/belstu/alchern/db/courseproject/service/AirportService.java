package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.Airport;
import by.belstu.alchern.db.courseproject.service.exception.AirportServiceException;

import java.util.List;

public interface AirportService {
    List<Airport> getAll() throws AirportServiceException;
}
