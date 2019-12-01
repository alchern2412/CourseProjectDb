package by.belstu.alchern.db.courseproject.controller.command.impl;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.Airport;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.exception.AirportServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        List<Airport> airportsList = null;
        try {
            airportsList = ServiceProvider.getInstance().getAirportService().getAll();
        } catch (AirportServiceException e) {
            throw new CommandException(e);
        }
        request.getSession().setAttribute(RequestParameterName.REQ_ATTR_ALL_PLACES, airportsList);

        String page = ConfigurationManager.getProperty("path.page.main");
        request.getRequestDispatcher(page).forward(request, response);
    }

}
