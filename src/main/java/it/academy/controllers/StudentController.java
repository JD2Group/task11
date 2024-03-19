package it.academy.controllers;

import it.academy.annotations.ControllerMapping;
import it.academy.annotations.PostMapping;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;
import it.academy.utils.ResponseHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.GSON;


@ControllerMapping(mappingUrl = "/student")
public class StudentController implements Controller {

    @PostMapping(url = "/save")
    public static void saveStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AdminService adminService = AdminServiceImpl.getInstance();

        String student = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        StudentDTORequest studentDTORequest = GSON.fromJson(student, StudentDTORequest.class);
        StudentDTOResponse out = adminService.createStudent(studentDTORequest);
        ResponseHelper.sendJsonResponse(response, out);

    }

    @PostMapping(url = "/update")
    public static void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminService adminService = AdminServiceImpl.getInstance();

        String student = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(student);
        StudentDTORequest studentDTORequest = GSON.fromJson(student, StudentDTORequest.class);
        StudentDTOResponse out = adminService.updateStudent(studentDTORequest);
        ResponseHelper.sendJsonResponse(response, out);
    }

    @PostMapping(url = "/delete")
    public static void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminService adminService = AdminServiceImpl.getInstance();
        String student = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Map<String, String> params = urlencodedParamExtractor(student);
        Long id = Long.parseLong(params.get("id"));
        System.out.println(id);
        StudentDTOResponse out = adminService.deleteStudent(id);
        ResponseHelper.sendJsonResponse(response, out);
    }

    private static Map<String, String> urlencodedParamExtractor(String str) {
        String[] strArr = str.split("&");
        Map<String, String> params = new HashMap<>();
        Arrays.stream(strArr)
                .forEach(s-> {
                    String[] arr = s.split("=");
                    params.put(arr[0], arr[1]);
                });
        return params;
    }
}
