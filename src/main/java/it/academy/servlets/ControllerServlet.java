package it.academy.servlets;

import it.academy.servlets.helpers.ActionFactory;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;

        ActionFactory actionFactory = new ActionFactory();
        ActionCommand actionCommand = actionFactory.defineCommand(req);

        page = actionCommand.execute(req);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }

//        Может позже
//        else {
//            page = ConfigurationManager.getProperty("path.page.index");
//            req.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullPage"));
//            resp.sendRedirect(req.getContextPath() + page);
//        }
    }
}
