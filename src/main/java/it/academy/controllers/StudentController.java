package it.academy.controllers;

import it.academy.dto.request.CommandRequest;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;
import static it.academy.utils.Constants.*;

public class StudentController implements Controller {
    private String action;

    public StudentController(String action) {
        this.action = action;
    }

    @Override
    public String execute(String req) {
        if (CREATE_ACTION.equals(action)) {
            return createStudent(req);
        } else if (UPDATE_ACTION.equals(action)) {
            return updateStudent(req);
        } else if (DELETE_ACTION.equals(action)) {
            return deleteStudent(req);
        }
        return null;
    }

    private String createStudent(String req) {
        AdminService service = AdminServiceImpl.getInstance();
        StudentDTORequest studentDTO = GSON.fromJson(req, StudentDTORequest.class);
        StudentDTOResponse out = service.createStudent(studentDTO);
        return GSON.toJson(out);
    }

    private String updateStudent(String req) {
        AdminService service = AdminServiceImpl.getInstance();
        StudentDTORequest studentDTO = GSON.fromJson(req, StudentDTORequest.class);
        StudentDTOResponse out = service.updateStudent(studentDTO);
        return GSON.toJson(out);
    }

    private String deleteStudent(String req) {
        AdminService service = AdminServiceImpl.getInstance();
        CommandRequest commandRequest = GSON.fromJson(req, CommandRequest.class);
        Long id = commandRequest.getId();
        StudentDTOResponse out = service.deleteStudent(id);
        return GSON.toJson(out);
    }

}
