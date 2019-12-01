package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.model.impl.Plain;
import by.belstu.alchern.db.courseproject.service.PlainService;
import by.belstu.alchern.db.courseproject.service.exception.PlainServiceException;

import java.util.List;

public class PlainServiceImpl implements PlainService {
    @Override
    public List<Plain> getAll() throws PlainServiceException {
        try {
            return DAOFactory.getInstance().getPlainDAO().getAll();
        } catch (DAOException e) {
            throw new PlainServiceException(e);
        }
    }
}
