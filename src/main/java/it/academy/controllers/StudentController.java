package it.academy.controllers;

import it.academy.dto.request.CommandRequest;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.*;

public class StudentController implements Controller {
    private final String action;

    public StudentController(String action) {
        this.action = action;
    }

    @Override
    public String execute(HttpServletRequest req) {
        switch (action) {
            case CREATE_ACTION: return createStudent(req);
            case UPDATE_ACTION: return updateStudent(req);
            case DELETE_ACTION: return deleteStudent(req);
            default: return null;
        }
    }

    private String createStudent(HttpServletRequest request) {
        AdminService service = AdminServiceImpl.getInstance();
        try {
            String req = request.getReader().lines().collect(Collectors.joining());
            StudentDTORequest studentDTO = GSON.fromJson(req, StudentDTORequest.class);
            StudentDTOResponse out = service.createStudent(studentDTO);
            return GSON.toJson(out);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private String updateStudent(HttpServletRequest request) {
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

    private String deleteStudent(HttpServletRequest request) {
        AdminService service = AdminServiceImpl.getInstance();
        System.out.println(request.getParameter("id"));
        if (request.getParameter("id") == null){
            return null;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        StudentDTOResponse out = service.deleteStudent(id);
        return GSON.toJson(out);
    }

}
