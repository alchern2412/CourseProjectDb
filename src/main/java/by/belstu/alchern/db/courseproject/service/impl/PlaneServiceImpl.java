package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.model.impl.Plane;
import by.belstu.alchern.db.courseproject.service.PlaneService;
import by.belstu.alchern.db.courseproject.service.exception.PlaneServiceException;

import java.util.List;

public class PlaneServiceImpl implements PlaneService {
    @Override
    public List<Plane> getAll() throws PlaneServiceException {
        try {
            return DAOFactory.getInstance().getPlaneDAO().getAll();
        } catch (DAOException e) {
            throw new PlaneServiceException(e);
        }
    }
}
