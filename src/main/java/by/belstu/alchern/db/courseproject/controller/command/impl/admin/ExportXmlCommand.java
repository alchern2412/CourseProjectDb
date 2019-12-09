package by.belstu.alchern.db.courseproject.controller.command.impl.admin;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.exception.CountryServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExportXmlCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);
        if (user != null && user.getRole().getRole().equals(RequestParameterName.USER_ROLE_ADMIN)) {
            String xmlExportText = null;
            try {
                xmlExportText = ServiceProvider.getInstance().getCountryService().exportXml();
            } catch (CountryServiceException e) {
                throw new CommandException(e);
            }
            request.getSession().setAttribute(RequestParameterName.REQ_ATTR_XML_EXPORT, xmlExportText);
            page = ConfigurationManager.getProperty("path.request.importexport");
            response.sendRedirect(page);
        } else {
            request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR, MessageManager.getProperty("message.error.notloggedin"));
            page = ConfigurationManager.getProperty("path.page.login");
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}
