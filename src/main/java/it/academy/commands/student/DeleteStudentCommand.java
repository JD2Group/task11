package it.academy.commands.student;

import com.google.gson.JsonSyntaxException;
import it.academy.commands.Command;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static it.academy.utils.Constants.ENTITY_ID;
import static it.academy.utils.Constants.GSON;

public class DeleteStudentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            AdminService service = AdminServiceImpl.getInstance();
            if (request.getParameter(ENTITY_ID) == null) {
                return null;
            }
            Long id = Long.valueOf(request.getParameter(ENTITY_ID));
            StudentDTOResponse out = service.deleteStudent(id);
            if (out == null){
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return null;
            }
            return GSON.toJson(out);
        } catch (Exception e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return null;
        }
    }

}
