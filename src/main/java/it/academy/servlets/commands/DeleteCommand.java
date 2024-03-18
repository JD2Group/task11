package it.academy.servlets.commands;

import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;
import it.academy.servlets.helpers.MessageManager;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.ERROR_PAGE_PATH;

public class DeleteCommand extends ChangePageCommand {
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter(STUDENT_ID));

        if (!service.deleteStudent(id)) {
            req.setAttribute(ERROR_PAGE_ATTRIBUTE, MessageManager.getProperty(DELETE_ERROR_MESSAGE));
            return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }

        return changePage(req);
    }
}
