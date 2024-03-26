package it.academy.servlet;

import it.academy.dto.StudentDTO;
import it.academy.service.AdminServise;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static it.academy.util.Constants.*;

@WebServlet(name = "ReadAllFromCountryServlet", urlPatterns = {"/getStud"})
public class ReadAllFromCountryServlet extends HttpServlet {

    private final AdminServise service = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int countOnPage = 0;
        int currentPage = 0;
        long countryId = 0;
        long totalCount;
        boolean isCountChanged = false;

        Object countOnPageFromSession;
        Object currentPageFromSession;
        Object countryIdFromSession;

        try {
            String countOnPageFromRequest = req.getParameter("countOnPage");
            if (countOnPageFromRequest == null || countOnPageFromRequest.trim().isEmpty()) {
                countOnPageFromSession = session.getAttribute("countOnPage");
                countOnPage = countOnPageFromSession == null ?
                                  DEFAULT_COUNT_ON_PAGE :
                                  (Integer) countOnPageFromSession;
            } else {
                countOnPage = Integer.parseInt(countOnPageFromRequest);
                isCountChanged = true;
            }

            String countryIdFromRequest = req.getParameter("countryId");
            if (countryIdFromRequest == null || countryIdFromRequest.trim().isEmpty()) {
                countryIdFromSession = session.getAttribute("countryId");
                countryId = (Long) countryIdFromSession;
            } else {
                countryId = Long.parseLong(countryIdFromRequest);
            }

            if (!isCountChanged) {
                String page = req.getParameter("currentPage");
                if (page == null || page.trim().isEmpty()) {
                    currentPageFromSession = session.getAttribute("currentPage");
                    currentPage = (Integer) (currentPageFromSession == null ?
                                                 DEFAULT_FIRST_PAGE_NUMBER :
                                                 currentPageFromSession);
                } else {
                    currentPage = Integer.parseInt(page);
                }
            } else {
                isCountChanged = false;
                currentPage = DEFAULT_FIRST_PAGE_NUMBER;
            }
        } catch (NumberFormatException e) {
            req.setAttribute("exception", e);
            resp.sendRedirect("/pages/exception.jsp");
        }

        List<StudentDTO> dtoList = new ArrayList<>();
        try {
            totalCount = service.getCountOfAllStudentsFromCountry(countryId);
            int maxPageNumber;
            if (totalCount != 0) {
                maxPageNumber = ((int) totalCount % countOnPage) == 0 ?
                                    (int) (totalCount / countOnPage) :
                                    (int) (totalCount / countOnPage) + 1;
            } else {
                maxPageNumber = DEFAULT_FIRST_PAGE_NUMBER;
            }

            if (currentPage > maxPageNumber || currentPage == DEFAULT_LAST_PAGE_NUMBER) {
                currentPage = maxPageNumber;
            } else if (currentPage == DEFAULT_ZERO_PAGE_NUMBER) {
                currentPage = DEFAULT_FIRST_PAGE_NUMBER;
            }

            dtoList = service.getListOfStudentsFromCountry(currentPage, countOnPage, countryId);
        } catch (Exception e) {
            req.setAttribute("exception", e);
            resp.sendRedirect("/pages/exception.jsp");
        }

        session.setAttribute("countOnPage", countOnPage);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("countryId", countryId);
        req.setAttribute("listDto", dtoList);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/pages/ListStudentsFromCountry.jsp");
        requestDispatcher.forward(req, resp);
    }
}
