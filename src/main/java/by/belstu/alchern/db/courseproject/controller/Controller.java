package by.belstu.alchern.db.courseproject.controller;

import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.TicketDAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.UserDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Flight;
import by.belstu.alchern.db.courseproject.model.impl.Ticket;
import by.belstu.alchern.db.courseproject.model.impl.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getSimpleName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        ActionFactory client = new ActionFactory();

        // define command coming from JSP
        ActionCommand command = client.defineCommand(request);


//        try {
//            List<User> users = DAOFactory.getInstance().getUserDAO().getAll();
//            List<Ticket> tickets = DAOFactory.getInstance().getTicketDAO().getAll();
//            users.toString();
//            tickets.toString();
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }

        try {
            command.execute(request, response);
        } catch ( Exception e) {
            LOGGER.error("Command error", e);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.error")).forward(request,response);
        }
    }
}
