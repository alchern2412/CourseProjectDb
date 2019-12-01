package by.belstu.alchern.db.courseproject.controller.command.impl.admin;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.model.impl.Airport;
import by.belstu.alchern.db.courseproject.model.impl.Flight;
import by.belstu.alchern.db.courseproject.model.impl.Plain;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.FlightService;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.exception.AirportServiceException;
import by.belstu.alchern.db.courseproject.service.exception.FlightServiceException;
import by.belstu.alchern.db.courseproject.service.exception.PlainServiceException;
import by.belstu.alchern.db.courseproject.view.dto.FlightDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateFlightCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);

        if (user.getRole().getRole().equals(RequestParameterName.USER_ROLE_ADMIN)) {
            if (request.getMethod().equals(RequestParameterName.METHOD_GET)) {
                List<Airport> placesList = null;
                List<Plain> plains = null;
                try {
                    plains = ServiceProvider.getInstance().getPlainService().getAll();
                    placesList = ServiceProvider.getInstance().getAirportService().getAll();
                } catch (AirportServiceException | PlainServiceException e) {
                    throw new CommandException(e);
                }
                request.setAttribute("allPlains", plains);
                request.setAttribute("allPlaces", placesList);

                page = ConfigurationManager.getProperty("path.page.createflight");
                request.getRequestDispatcher(page).forward(request, response);
            } else if (request.getMethod().equals(RequestParameterName.METHOD_POST)) {
                FlightDTO flightDTO = new FlightDTO();

                flightDTO.setFromAirport(request.getParameter(RequestParameterName.REQ_PARAM_FROM_PLACE));
                flightDTO.setToAirport(request.getParameter(RequestParameterName.REQ_PARAM_TO_PLACE));
                flightDTO.setDeparture(request.getParameter(RequestParameterName.REQ_PARAM_DEPARTURE_DATE));
                flightDTO.setArrival(request.getParameter(RequestParameterName.REQ_PARAM_ARRIVAL_DATE));
                flightDTO.setPrice(request.getParameter(RequestParameterName.REQ_PARAM_PRICE_FLIGHT));
                flightDTO.setPlain(request.getParameter(RequestParameterName.REQ_PARAM_PLAIN)); // add on jsp!!!

                FlightService flightService = ServiceProvider.getInstance().getFlightService();


                Flight flight = null;
                try {
                    flight = flightService.create(flightDTO);
                } catch (FlightServiceException e) {
                    throw new CommandException(e);
                }

                if (flight != null) {
                    request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_SUCCESS,
                            MessageManager.getProperty("message.success.flightcreated"));
                    page = ConfigurationManager.getProperty("path.page.createflight");
                } else {
                    request.setAttribute("errorLoginPassMessage",
                            MessageManager.getProperty("message.error.loginerror"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }
                if (page != null) {
                    request.getRequestDispatcher(page).forward(request, response);
                } else {
                    page = ConfigurationManager.getProperty("path.page.index");
                    request.getSession().setAttribute("nullPage",
                            MessageManager.getProperty("message.error.notfound"));
                    response.sendRedirect(request.getContextPath() + page);
                }
            }
        }

    }
}
