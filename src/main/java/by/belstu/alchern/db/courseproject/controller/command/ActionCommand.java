package by.belstu.alchern.db.courseproject.controller.command;

import by.belstu.alchern.db.courseproject.controller.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException;
}
