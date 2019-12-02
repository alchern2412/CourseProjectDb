package by.belstu.alchern.db.courseproject.controller.command.impl.admin;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
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
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditFlightCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(EditFlightCommand.class.getSimpleName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);

        if (user.getRole().getRole().equals(RequestParameterName.USER_ROLE_ADMIN)) {
            Flight flight = null;
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

                int flightId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_ID));
                try {
                    flight = ServiceProvider.getInstance().getFlightService().get(flightId);
                } catch (FlightServiceException e) {
                    throw new CommandException(e);
                }
                request.setAttribute(RequestParameterName.REQ_PARAM_FLIGHT, flight);
                page = ConfigurationManager.getProperty("path.page.editflight");
                LOGGER.info("Edit flight");
                request.getRequestDispatcher(page).forward(request, response);
            } else {

                String fromPlace = request.getParameter(RequestParameterName.REQ_PARAM_FROM_PLACE);
                String toPlace = request.getParameter(RequestParameterName.REQ_PARAM_TO_PLACE);
                String departureDate = request.getParameter(RequestParameterName.REQ_PARAM_DEPARTURE_DATE);
                String arrivalDate = request.getParameter(RequestParameterName.REQ_PARAM_ARRIVAL_DATE);
                String priceFlight = request.getParameter(RequestParameterName.REQ_PARAM_PRICE_FLIGHT);
                String plain = request.getParameter(RequestParameterName.REQ_PARAM_PLAIN);

                int flightId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_ID));
                try {
                    flight = ServiceProvider.getInstance().getFlightService().get(flightId);
                } catch (FlightServiceException e) {
                    throw new CommandException(e);
                }
                FlightDTO flightDTO = new FlightDTO();

                flightDTO.setDeparture(departureDate);
                flightDTO.setArrival(arrivalDate);
                flightDTO.setFromAirport(fromPlace);
                flightDTO.setToAirport(toPlace);
                flightDTO.setPrice(priceFlight);
                flightDTO.setPlain(plain);

                FlightService flightService = ServiceProvider.getInstance().getFlightService();


                try {
                    flight = flightService.edit(flight, flightDTO);
                } catch (FlightServiceException e) {
                    throw new CommandException(e);
                }

                if (flight != null) {
                    request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_SUCCESS,
                            MessageManager.getProperty("message.success.flightedited"));
                    page = ConfigurationManager.getProperty("path.request.allflights");
                } else {
                    request.setAttribute("errorLoginPassMessage",
                            MessageManager.getProperty("message.error.loginerror"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }
                if (page != null) {
                    response.sendRedirect(request.getContextPath() + page);
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
