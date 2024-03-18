package it.academy.servlets.commands;

import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;
import javax.servlet.http.HttpServletRequest;

public class DeleteCommand extends ChangePageCommand {
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        service.deleteStudent(id);
        return changePage(req);
    }
}
