package it.academy.servlet;

import it.academy.service.AdminServise;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCountryServlet", urlPatterns = "/delCountry")
public class DeleteCountryServlet extends HttpServlet {

    private final AdminServise service = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String string = req.getParameter("id");
            Long longId = Long.parseLong(string);

            service.deleteCountry(longId);
            resp.sendRedirect("getAllCountries");
        } catch (Exception e) {
            req.setAttribute("exception", e);
            resp.sendRedirect("/pages/exception.jsp");
        }
    }
}

