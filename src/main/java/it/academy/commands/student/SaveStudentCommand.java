package it.academy.commands.student;

import it.academy.commands.Command;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.GSON;

public class SaveStudentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminService service = AdminServiceImpl.getInstance();
        try {
            String req = request.getReader().lines().collect(Collectors.joining());
            StudentDTORequest studentDTO = GSON.fromJson(req, StudentDTORequest.class);
            StudentDTOResponse out = service.createStudent(studentDTO);
            if (out == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return null;
            }
            return GSON.toJson(out);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return null;
        }
    }

}
