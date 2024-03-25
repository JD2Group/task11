package it.academy.servlets;

import it.academy.commands.Command;
import it.academy.commands.factory.CommandFactory;
import it.academy.utils.Constants;
import it.academy.utils.ResponseHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/api/*", name = "MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp, "POST");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp, "GET");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String method) throws IOException {

        Enumeration<String> test = request.getHeaderNames();
        while (test.hasMoreElements()) {
            String elem = test.nextElement();
            System.out.println(elem + " : " + request.getHeader(elem));

        }

        String command = method + "_" + request.getHeader(Constants.COMMAND_HEADER);
        System.out.println("Cur command: " + command);
        CommandFactory commandFactory = CommandFactory.getFactory();
        Command currentCommand = commandFactory.defineController(command);
        if (currentCommand == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Command is undefined.");
            return;
        }
        System.out.println("Controller has been identified.");

        String resp = currentCommand.execute(request);
        if (resp == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        System.out.println("Response not null: " + resp);


        ResponseHelper.sendJsonResponse(response, resp);

    }
}
