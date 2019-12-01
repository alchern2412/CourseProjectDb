package by.belstu.alchern.db.courseproject.controller.command.impl;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.UserDAOException;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.UserService;
import by.belstu.alchern.db.courseproject.service.exception.UserServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;

        if (request.getMethod().equals(RequestParameterName.METHOD_GET)) {
            page = ConfigurationManager.getProperty("path.page.login");
            request.getRequestDispatcher(page).forward(request, response);
        } else if (request.getMethod().equals(RequestParameterName.METHOD_POST)) {
            String login;
            String password;

            login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
            password = request.getParameter(RequestParameterName.REQ_PARAM_PASSWORD);

            UserService userService = ServiceProvider.getInstance().getUserService();
            User user = null;

            try {
                user = userService.login(login, password);
            } catch (UserServiceException e) {
                throw new CommandException(e);
            }

            if (user != null) {
                request.getSession().setAttribute("user", user);
//                List<Place> placesList = null;
//                try {
//                    placesList = ServiceProvider.getInstance().getPlaceService().getAllPlaces();
//                } catch (PlaceServiceException e) {
//                    throw new CommandException(e);
//                }
//                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_ALL_PLACES, placesList);
                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_SUCCESS,
                        MessageManager.getProperty("message.success.loggedin"));
//                page = ConfigurationManager.getProperty("path.page.main");
                page = ConfigurationManager.getProperty("path.request.main");
            } else {
                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR,
                        MessageManager.getProperty("message.error.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }

            if (page != null) {
                response.sendRedirect(request.getContextPath() + page);
//                request.getRequestDispatcher(page).forward(request, response);
            } else {
                page = ConfigurationManager.getProperty("path.page.index");
                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR,
                        MessageManager.getProperty("message.error.notfound"));
                request.getRequestDispatcher(page).forward(request, response);
            }
        }


    }
}
