package by.belstu.alchern.db.courseproject.controller.command.impl.user;

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

public class UserOrdersCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page;

        User user = (User)request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);

        if (user != null) {
            TicketService ticketService = ServiceProvider.getInstance().getTicketService();
            List<Ticket> tickets = null;
            try {
                tickets = ticketService.getTicketsByUserId(user.getId());
            } catch (TicketServiceException e) {
                throw new CommandException(e);
            }
            List<Ticket> finishedTickets = ticketService.getFinishedTicketsFromAll(tickets);
            tickets = ticketService.getUnfinishedTicketsFromAll(tickets);

            request.setAttribute("userTickets", tickets);
            request.setAttribute("userFinishedTickets", finishedTickets);

            page = ConfigurationManager.getProperty("path.page.orders");
        } else {
            request.getSession().setAttribute("errorMessage", MessageManager.getProperty("message.error.notloggedin"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            // вызов страницы ответа на запрос
            dispatcher.forward(request, response);
            //return page;
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.error.notfound"));
            // change
            //response.setHeader("X-Error-Message", MessageManager.getProperty("message.notloggedin"));
            response.sendRedirect(request.getContextPath() + page);
        }

    }
}
