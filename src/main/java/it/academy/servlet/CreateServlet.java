package it.academy.servlet;

import it.academy.dto.StudentDTO;
import it.academy.service.AdminServise;
import it.academy.service.impl.AdminServiceImpl;
import it.academy.util.IntegerConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateServlet", urlPatterns = {"/create"})
public class CreateServlet extends HttpServlet {

    private final AdminServise service = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/createForm.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            service.createStudent(StudentDTO.builder()
                                      .name(req.getParameter("name").trim())
                                      .surname(req.getParameter("surname").trim())
                                      .age(IntegerConverter.convertToInt(req.getParameter("age").trim()))
                                      .city(req.getParameter("city").trim())
                                      .street(req.getParameter("street").trim())
                                      .building(IntegerConverter.convertToInt(req.getParameter("building").trim()))
                                      .mark(IntegerConverter.convertToInt(req.getParameter("mark").trim()))
                                      .build());
            resp.sendRedirect("readAll");
        } catch (Exception e) {
            resp.sendRedirect("exception.jsp");
        }
    }
}
