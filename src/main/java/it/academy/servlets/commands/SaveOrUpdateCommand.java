package it.academy.servlets.commands;

import it.academy.dto.StudentDTO;
import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;
import it.academy.servlets.helpers.MessageManager;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class SaveOrUpdateCommand extends ChangePageCommand {
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        StudentDTO studentDTO = StudentDTO.builder()
                .id(Long.parseLong(req.getParameter(STUDENT_ID)))
                .name(req.getParameter(STUDENT_NAME))
                .surname(req.getParameter(STUDENT_SURNAME))
                .age(Integer.parseInt(req.getParameter(STUDENT_AGE)))
                .mark(Integer.parseInt(req.getParameter(STUDENT_MARK)))
                .city(req.getParameter(STUDENT_CITY))
                .street(req.getParameter(STUDENT_STREET))
                .houseNumber(Integer.parseInt(req.getParameter(STUDENT_HOUSE)))
                .build();

        if (!service.saveOrUpdateStudent(studentDTO)) {
            req.setAttribute(ERROR_PAGE_ATTRIBUTE, MessageManager.getProperty(UPDATE_ERROR_MESSAGE));
            return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }

        return changePage(req);
    }
}