package by.belstu.alchern.db.courseproject.dao;

import by.belstu.alchern.db.courseproject.dao.exception.impl.CountryDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Country;

public interface CountryDAO extends EntityDAO<Country> {
    String exportXml() throws CountryDAOException;
    void importXml(String path) throws CountryDAOException;
}
