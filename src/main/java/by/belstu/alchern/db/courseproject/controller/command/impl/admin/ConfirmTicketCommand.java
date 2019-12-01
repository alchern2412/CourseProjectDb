package by.belstu.alchern.db.courseproject.controller.command.impl.admin;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.exception.TicketServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmTicketCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(ConfirmTicketCommand.class.getSimpleName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);

        if (user != null) {
            if (user.getRole().getRole().equals(RequestParameterName.USER_ROLE_ADMIN)) {
                int ticketId = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_TICKET_ID));

                try {
                    ServiceProvider.getInstance().getTicketService().confirmTicket(ticketId);
                } catch (TicketServiceException e) {
                    throw new CommandException(e);
                }
                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_SUCCESS,
                        MessageManager.getProperty("message.success.confirmticket"));
                page = ConfigurationManager.getProperty("path.request.alltickets");
                LOGGER.info("Confirm ticket");


            } else {
                // change
                LOGGER.warn("User is not admin");
                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR, MessageManager.getProperty("message.error.norights"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
            if (page != null) {
                LOGGER.info("User is not authorized");
                response.sendRedirect(request.getContextPath() + page);
                //request.getRequestDispatcher(page).forward(request, response);
            } else {
                page = ConfigurationManager.getProperty("path.page.index");
                request.getSession().setAttribute("nullPage",
                        MessageManager.getProperty("message.error.notfound"));
                response.sendRedirect(request.getContextPath() + page);
            }
        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.error.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
            request.getRequestDispatcher(page).forward(request, response);

        }


    }
}
