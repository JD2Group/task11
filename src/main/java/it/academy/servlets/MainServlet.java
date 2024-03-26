package it.academy.servlets;

import it.academy.commands.Command;
import it.academy.commands.factory.CommandFactory;
import it.academy.utils.Constants;
import it.academy.utils.ResponseHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@WebServlet(urlPatterns = "/api/*", name = "MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp, Constants.POST_METHOD);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp, Constants.GET_METHOD);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String method) throws IOException {

        System.out.println("\n///");
        Enumeration<String> test = request.getHeaderNames();
        while (test.hasMoreElements()) {
            String elem = test.nextElement();
            System.out.println(elem + " : " + request.getHeader(elem));

        }
        System.out.println("///\n");
        String reqURI = request.getRequestURI();
        if (reqURI.contains(Constants.PARAMETER_KEY)) {
            reqURI = reqURI.replace(reqURI.substring(reqURI.lastIndexOf(Constants.PARAMETER_KEY)), "");
        }
        String commandValid = String.format(Constants.COMMAND_PATTERN, method, reqURI.substring(reqURI.lastIndexOf("/") + 1));
        String command = String.format(Constants.COMMAND_PATTERN, method, request.getHeader(Constants.COMMAND_HEADER));
        if (!command.equals(commandValid)) {
            log.error(Constants.COMMAND_INVALID_ERROR);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, Constants.COMMAND_INVALID_ERROR);
            return;
        }
        System.out.println("Cur command: " + command);
        CommandFactory commandFactory = CommandFactory.getFactory();
        Command currentCommand = commandFactory.defineController(command);
        if (currentCommand == null) {
            log.error(Constants.UNDEFINED_COMMAND_ERROR);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, Constants.UNDEFINED_COMMAND_ERROR);
            return;
        }
        System.out.println("Controller has been identified.");

        String resp = currentCommand.execute(request, response);
        if (resp == null) {
            log.error(Constants.INVALID_RESPONSE_ERROR);
            return;
        }
        System.out.println("Response not null: " + resp);


        ResponseHelper.sendJsonResponse(response, resp);

    }
}
