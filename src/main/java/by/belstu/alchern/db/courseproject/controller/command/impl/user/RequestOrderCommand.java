package by.belstu.alchern.db.courseproject.controller.command.impl.user;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.TicketService;
import by.belstu.alchern.db.courseproject.service.exception.TicketServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestOrderCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page;

        User user = (User)request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);
        String flightId = request.getParameter(RequestParameterName.REQ_PARAM_SELECTED_FLIGHT);
        Boolean luggage = new Boolean(request.getParameter(RequestParameterName.REQ_PARAM_LUGGAGE));

        if (user != null) {
            TicketService ticketService = ServiceProvider.getInstance().getTicketService();
            try {
                ticketService.createTicket(user, flightId);
            } catch (TicketServiceException e) {
                throw new CommandException(e);
            }

            request.getSession().setAttribute("successMessage",
                    MessageManager.getProperty("message.success.successorder"));
            page = ConfigurationManager.getProperty("path.request.orders");
            response.sendRedirect(page);
        } else {
            request.getSession().setAttribute("errorMessage",
                    MessageManager.getProperty("message.error.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
//            request.getSession().setAttribute("nullPage",
//                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
