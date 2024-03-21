package it.academy.servlet;

import it.academy.dto.StudentDTO;
import it.academy.service.AdminServise;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    private final AdminServise service = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String string = req.getParameter("id");
            Long longId = Long.parseLong(string);

            service.deleteStudent(longId);
            resp.sendRedirect("readAll");
        } catch (Exception e) {
            resp.sendRedirect("exception.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String string = req.getParameter("id");
            Long longId = Long.parseLong(string);

            StudentDTO studentDTO=service.getById(longId);
            req.setAttribute("studentDTO",studentDTO);

            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/deleteWindow.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("exception.jsp");
        }
    }
}
