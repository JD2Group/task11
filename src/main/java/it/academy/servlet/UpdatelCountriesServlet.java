package it.academy.servlet;

import it.academy.dto.CountryDTO;
import it.academy.service.AdminServise;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdatelCountriesServlet", urlPatterns = "/updCountry")
public class UpdatelCountriesServlet extends HttpServlet {

    AdminServise service = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String string = req.getParameter("id");
            Long longId = Long.parseLong(string);

            CountryDTO countryDTO = service.getCountryBYId(longId);

            req.setAttribute("countryDTO", countryDTO);

            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/pages/updateCountryForm.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("exception", e);
            resp.sendRedirect("/pages/exception.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            service.updateCountry(CountryDTO.builder()
                                      .id(Long.parseLong(req.getParameter("id").trim()))
                                      .countryName(req.getParameter("countryName").trim())
                                      .build());
            resp.sendRedirect("getAllCountries");
        } catch (Exception e) {
            req.setAttribute("exception", e);
            resp.sendRedirect("/pages/exception.jsp");
        }
    }
}