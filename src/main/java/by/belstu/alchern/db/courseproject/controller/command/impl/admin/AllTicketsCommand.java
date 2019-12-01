package by.belstu.alchern.db.courseproject.controller.command.impl.admin;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.Ticket;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.TicketService;
import by.belstu.alchern.db.courseproject.service.exception.TicketServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllTicketsCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page;

        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);
        int pageNum = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_PAGE));

        if (user != null) {
            if (user.getRole().getRole().equals(RequestParameterName.USER_ROLE_ADMIN)) {
                TicketService ticketService = ServiceProvider.getInstance().getTicketService();
                List<Ticket> tickets = null;
                try {
                    tickets = ticketService.getByPageNum(pageNum);
                } catch (TicketServiceException e) {
                    throw new CommandException(e);
                }


                //change
                request.setAttribute(RequestParameterName.REQ_ATTR_TICKETS, tickets);

                page = ConfigurationManager.getProperty("path.page.alltickets");
            } else {
                // change
                request.getSession().setAttribute("errorMessage", MessageManager.getProperty("message.error.norights"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } else {
            request.getSession().setAttribute("errorMessage", MessageManager.getProperty("message.error.notloggedin"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
//            page = ConfigurationManager.getProperty("path.page.index");
//            request.getSession().setAttribute("nullPage",
//                    MessageManager.getProperty("message.nullpage"));
//            // change
//            //response.setHeader("X-Error-Message", MessageManager.getProperty("message.notloggedin"));
//            response.sendRedirect(getBasePath(request) + page);
        }
    }
}
