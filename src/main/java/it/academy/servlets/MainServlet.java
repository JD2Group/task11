package it.academy.servlets;

import it.academy.controllers.Controller;
import it.academy.controllers.factory.ControllerFactory;
import it.academy.dto.request.CommandRequest;
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
        String resp;

        String req = request.getReader().lines().collect(Collectors.joining());
        String command = GSON.fromJson(req, CommandRequest.class).getCommand();

        ControllerFactory controllerFactory = new ControllerFactory();
        Controller controller =  controllerFactory.defineCommand(command);

        resp = controller.execute(req);

        ResponseHelper.sendJsonResponse(response, resp);
    }
}
