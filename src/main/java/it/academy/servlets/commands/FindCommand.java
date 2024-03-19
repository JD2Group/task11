package it.academy.servlets.commands;

import it.academy.dto.StudentDTO;
import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.LIST_PAGE_PATH;

public class FindCommand extends ChangePageCommand{
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        int currentPage = Integer.parseInt(req.getParameter(PAGE_ATTRIBUTE));
        String parameter = req.getParameter(STUDENT_PARAMETER_ATTRIBUTE);
        String filter = req.getParameter(FILTER_ATTRIBUTE);

        if (!parameter.isEmpty()) {
            List<StudentDTO> students = service.findStudentsByParameter(parameter, filter);
            req.setAttribute(STUDENTS_ATTRIBUTE, students);
            req.setAttribute(PAGE_ATTRIBUTE, currentPage);
            req.setAttribute(MAX_PAGE_ATTRIBUTE, service.getMaxPageNumber());
        } else {
            return changePage(req);
        }

        return ConfigurationManager.getProperty(LIST_PAGE_PATH);
    }

}
