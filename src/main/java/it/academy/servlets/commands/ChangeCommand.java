package it.academy.servlets.commands;

import it.academy.dto.StudentDTO;
import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;
import javax.servlet.http.HttpServletRequest;

public class ChangeCommand implements ActionCommand {
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        StudentDTO studentDTO = service.findStudent(Long.parseLong(req.getParameter("id")));
        req.setAttribute("student", studentDTO);
        req.setAttribute("page", req.getParameter("page"));
        return ConfigurationManager.getProperty("path.page.student");
    }
}
