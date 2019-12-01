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

public class DeleteOrderCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page;

        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);
        int idDeleteTicket = Integer.parseInt(request.getParameter(RequestParameterName.REQ_PARAM_DELETE_TICKET));


        if (user != null) {
            TicketService ticketService = ServiceProvider.getInstance().getTicketService();
            try {
                ticketService.deleteTicket(idDeleteTicket);
            } catch (TicketServiceException e) {
                throw new CommandException(e);
            }
            request.getSession().setAttribute("successMessage",
                    MessageManager.getProperty("message.success.orderdeleted"));

            page = ConfigurationManager.getProperty("path.request.orders");
        } else {
//            request.setAttribute("errorMessage",
//                    MessageManager.getProperty("message.notloggedin"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        if (page != null) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
////             вызов страницы ответа на запрос
//            dispatcher.forward(request, response);
////            return page;
            response.sendRedirect(request.getContextPath() + page);


        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.error.notfound"));

            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
