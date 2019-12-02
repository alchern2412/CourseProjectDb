package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.Flight;
import by.belstu.alchern.db.courseproject.service.exception.FlightServiceException;
import by.belstu.alchern.db.courseproject.view.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    List<Flight> getByPageNum(int pageNum) throws FlightServiceException;

    List<Flight> getFlightsByRequest(FlightDTO flightDTO) throws FlightServiceException;

    Flight create(FlightDTO flightDTO) throws FlightServiceException;

    Flight get(int flightId) throws FlightServiceException;

    Flight edit(Flight flight, FlightDTO flightDTO) throws FlightServiceException;
}
