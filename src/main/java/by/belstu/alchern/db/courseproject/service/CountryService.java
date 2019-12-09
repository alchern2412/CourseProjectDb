package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.service.exception.CountryServiceException;

public interface CountryService {
    String exportXml() throws CountryServiceException;

    void importXml(String fileName) throws CountryServiceException;
}
