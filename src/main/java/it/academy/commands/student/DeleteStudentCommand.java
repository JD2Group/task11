package it.academy.commands.student;

import it.academy.commands.Command;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.ENTITY_ID;
import static it.academy.utils.Constants.GSON;

public class DeleteStudentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        AdminService service = AdminServiceImpl.getInstance();
        if (request.getParameter(ENTITY_ID) == null) {
            return null;
        }
        Long id = Long.valueOf(request.getParameter(ENTITY_ID));
        StudentDTOResponse out = service.deleteStudent(id);
        return GSON.toJson(out);
    }

}
