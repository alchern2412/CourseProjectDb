package by.belstu.alchern.db.courseproject.controller.command.impl;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.Flight;
import by.belstu.alchern.db.courseproject.service.FlightService;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.exception.AirportServiceException;
import by.belstu.alchern.db.courseproject.service.exception.FlightServiceException;
import by.belstu.alchern.db.courseproject.view.dto.FlightDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindFlightCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page;

        String fromPlace = request.getParameter(RequestParameterName.REQ_PARAM_FROM_PLACE);
        String toPlace = request.getParameter(RequestParameterName.REQ_PARAM_TO_PLACE);
        String departureDate = request.getParameter(RequestParameterName.REQ_PARAM_DEPARTURE_DATE);

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setDeparture(departureDate);
        flightDTO.setFromAirport(fromPlace);
        flightDTO.setToAirport(toPlace);

        FlightService flightService = ServiceProvider.getInstance().getFlightService();

        List<Flight> flights = null;
        try {
            flights = flightService.getFlightsByRequest(flightDTO);
        } catch (FlightServiceException e) {
            throw new CommandException(e);
        }

        if (flights != null) {
            request.setAttribute("findedFlights", flights);
            page = ConfigurationManager.getProperty("path.page.find.flights");
        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.error.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.error.notfound"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
