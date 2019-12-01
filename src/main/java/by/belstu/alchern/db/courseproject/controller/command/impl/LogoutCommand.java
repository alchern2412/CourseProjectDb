package by.belstu.alchern.db.courseproject.controller.command.impl;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = ConfigurationManager.getProperty("path.request.main");
        String language = (String)request.getSession().getAttribute(RequestParameterName.REQ_ATTR_LANGUAGE);
        request.getSession().invalidate();
        request.getSession().setAttribute(RequestParameterName.REQ_ATTR_LANGUAGE, language);
        request.getSession().setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_SUCCESS,
                MessageManager.getProperty("message.success.loggedout"));
        response.sendRedirect(request.getContextPath() + page);
    }
}
