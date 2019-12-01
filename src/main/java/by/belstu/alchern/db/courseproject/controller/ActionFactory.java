package by.belstu.alchern.db.courseproject.controller;

import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.CommandEnum;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.impl.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand(); // by default

        String action = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
        if (action == null || action.isEmpty()) {
            return current;
        }
        action = action.toUpperCase();
        action = action.replace('-', '_');
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action);
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(RequestParameterName.REQ_ATTR_MESSAGE_ERROR, action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
