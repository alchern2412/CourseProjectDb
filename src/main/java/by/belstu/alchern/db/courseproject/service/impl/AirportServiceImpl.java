package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.model.impl.Airport;
import by.belstu.alchern.db.courseproject.service.AirportService;
import by.belstu.alchern.db.courseproject.service.exception.AirportServiceException;

import java.util.List;

public class AirportServiceImpl implements AirportService {
    @Override
    public List<Airport> getAll() throws AirportServiceException {
        try {
            return DAOFactory.getInstance().getAirportDAO().getAll();
        } catch (DAOException e) {
            throw new AirportServiceException(e);
        }
    }
}
