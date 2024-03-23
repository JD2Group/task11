package it.academy.commands.student;

import it.academy.commands.Command;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.GSON;

public class UpdateStudentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        AdminService service = AdminServiceImpl.getInstance();
        try {
            String req = request.getReader().lines().collect(Collectors.joining());
            StudentDTORequest studentDTO = GSON.fromJson(req, StudentDTORequest.class);
            StudentDTOResponse out = service.updateStudent(studentDTO);
            return GSON.toJson(out);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
