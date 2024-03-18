package it.academy.servlets.commands;

import it.academy.dto.StudentDTO;
import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ChangeCommand implements ActionCommand {
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        StudentDTO studentDTO = service.findStudent(Long.parseLong(req.getParameter(STUDENT_ID)));
        req.setAttribute(STUDENT_ATTRIBUTE, studentDTO);
        req.setAttribute(PAGE_ATTRIBUTE, req.getParameter(PAGE_ATTRIBUTE));
        return ConfigurationManager.getProperty(STUDENT_PAGE_PATH);
    }
}
