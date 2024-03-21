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

@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {
    private final AdminServise service = new AdminServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String string = req.getParameter("id");
            Long longId = Long.parseLong(string);

            StudentDTO studentDTO = service.getById(longId);

            req.setAttribute("studentDTO", studentDTO);

            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/updateForm.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            resp.sendRedirect("exception.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            service.updateStudent(StudentDTO.builder()
                                      .id(Long.parseLong(req.getParameter("id").trim()))
                                      .name(req.getParameter("name").trim())
                                      .surname(req.getParameter("surname").trim())
                                      .age(Integer.parseInt(req.getParameter("age").trim()))
                                      .city(req.getParameter("city").trim())
                                      .street(req.getParameter("street").trim())
                                      .building(Integer.parseInt(req.getParameter("building").trim()))
                                      .mark(Integer.parseInt(req.getParameter("mark").trim()))
                                      .build());
            resp.sendRedirect("readAll");
        } catch (Exception e) {
            resp.sendRedirect("exception.jsp");
        }
    }
}
