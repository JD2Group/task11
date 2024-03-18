package it.academy.servlets.commands;

import it.academy.dto.StudentDTO;
import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static it.academy.utils.Constants.LIST_SIZE;

public class ChangePageCommand implements ActionCommand {
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        return changePage(req);
    }

    protected String changePage(HttpServletRequest req) {
        int currentPage = Integer.parseInt(req.getParameter("page"));

        List<StudentDTO> students = service.getStudentsForPage(currentPage, LIST_SIZE);
        req.setAttribute("students", students);
        req.setAttribute("page", currentPage);
        req.setAttribute("maxPage", service.getMaxPageNumber());

        return ConfigurationManager.getProperty("path.page.list");
    }

}
