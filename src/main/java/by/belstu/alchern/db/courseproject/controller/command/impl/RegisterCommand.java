package by.belstu.alchern.db.courseproject.controller.command.impl;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.UserService;
import by.belstu.alchern.db.courseproject.service.exception.UserServiceException;
import by.belstu.alchern.db.courseproject.view.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;

        if (request.getMethod().equals(RequestParameterName.METHOD_GET)) {
            page = ConfigurationManager.getProperty("path.page.register");
            request.getRequestDispatcher(page).forward(request, response);
        } else if (request.getMethod().equals(RequestParameterName.METHOD_POST)) {
            UserDTO userDTO = new UserDTO();

            userDTO.setLogin(request.getParameter(RequestParameterName.REQ_PARAM_LOGIN));
            userDTO.setPassword(request.getParameter(RequestParameterName.REQ_PARAM_PASSWORD));
            userDTO.setFirstName(request.getParameter(RequestParameterName.REQ_PARAM_FIRST_NAME));
            userDTO.setLastName(request.getParameter(RequestParameterName.REQ_PARAM_LAST_NAME));
            userDTO.setDocumentNumber(request.getParameter(RequestParameterName.REQ_PARAM_DOCUMENT_NUMBER));
            userDTO.setTel(request.getParameter(RequestParameterName.REQ_PARAM_PHONE_NUMBER));
            userDTO.setAddress(request.getParameter(RequestParameterName.REQ_PARAM_ADDRESS));

            // COUNTINUE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


            UserService userService = ServiceProvider.getInstance().getUserService();
            User user = null;
            try {
                user = userService.register(userDTO);
            } catch (UserServiceException e) {
                throw new CommandException(e);
            }

            try {
                user = userService.login(userDTO.getLogin(), userDTO.getPassword());
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
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR,
                        MessageManager.getProperty("message.error.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }

            if (page != null) {
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                page = ConfigurationManager.getProperty("path.page.index");
                request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR,
                        MessageManager.getProperty("message.error.notfound"));
                request.getRequestDispatcher(page).forward(request, response);
            }
        }
    }
}
