package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.impl.CountryDAOException;
import by.belstu.alchern.db.courseproject.service.CountryService;
import by.belstu.alchern.db.courseproject.service.exception.CountryServiceException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CountryServiceImpl implements CountryService {
    @Override
    public String exportXml() throws CountryServiceException {
        try {
            return DAOFactory.getInstance().getCountryDAO().exportXml();
        } catch (CountryDAOException e) {
            throw new CountryServiceException(e);
        }
    }

    @Override
    public void importXml(String xmlText) throws CountryServiceException {
        String fileName = "data.xml";
        String fullPath = "";
        try {
            File file = new File(fileName);
            fullPath = file.getAbsolutePath();
            FileWriter writer = new FileWriter(file, false);
            writer.write(xmlText);
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            throw new CountryServiceException(ex);
        }

        try {

            DAOFactory.getInstance().getCountryDAO().importXml(fullPath);
        } catch (CountryDAOException e) {
            throw new CountryServiceException(e);
        }
    }
}
