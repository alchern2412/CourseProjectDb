package by.belstu.alchern.db.courseproject.controller.command.impl.admin;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExportImportXmlCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);
        if (user != null && user.getRole().getRole().equals(RequestParameterName.USER_ROLE_ADMIN)) {
            page = ConfigurationManager.getProperty("path.page.importexport");
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR, MessageManager.getProperty("message.error.notloggedin"));
            page = ConfigurationManager.getProperty("path.page.login");
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}
