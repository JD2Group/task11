package it.academy.servlets;

import it.academy.controllers.Controller;
import it.academy.controllers.factory.ControllerFactory;
import it.academy.dto.request.CommandRequest;
import it.academy.utils.Constants;
import it.academy.utils.ResponseHelper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.GSON;

@WebServlet(urlPatterns = "/api/*", name = "MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String command = request.getHeader(Constants.COMMAND_HEADER);

        ControllerFactory controllerFactory = ControllerFactory.getFactory();
        Controller controller =  controllerFactory.defineCommand(command);
        if (controller == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Command is undefined.");
            return;
        }
        System.out.println("Controller has been identified.");

        String resp = controller.execute(request);
        if (resp == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        System.out.println("Response not null: " + resp);


        ResponseHelper.sendJsonResponse(response, resp);

    }
}
