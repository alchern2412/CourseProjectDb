package by.belstu.alchern.db.courseproject.controller.command.impl.admin;

import by.belstu.alchern.db.courseproject.controller.RequestParameterName;
import by.belstu.alchern.db.courseproject.controller.command.ActionCommand;
import by.belstu.alchern.db.courseproject.controller.command.ConfigurationManager;
import by.belstu.alchern.db.courseproject.controller.command.MessageManager;
import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.ServiceProvider;
import by.belstu.alchern.db.courseproject.service.exception.CountryServiceException;
import sun.misc.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImportXmlCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String page = null;
        User user = (User) request.getSession().getAttribute(RequestParameterName.REQ_PARAM_USER);
        if (user != null && user.getRole().getRole().equals(RequestParameterName.USER_ROLE_ADMIN)) {
//            String xmlImportFilePath = request.getParameter(RequestParameterName.REQ_PARAM_XML_IMPORT_FILE);
            Part filePart = request.getPart(RequestParameterName.REQ_PARAM_XML_IMPORT_FILE); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            Scanner s = new Scanner(fileContent).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            try {
                ServiceProvider.getInstance().getCountryService().importXml(result);
            } catch (CountryServiceException e) {
                throw new CommandException(e);
            }

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
