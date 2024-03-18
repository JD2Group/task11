package it.academy.service;

import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;

import java.util.List;

public interface AdminService {

    List<StudentDTORequest> getAllStudents();

    List<StudentDTORequest> getAllStudents(int page, int count);

    StudentDTOResponse createStudent(StudentDTORequest studentDTORequest);

    StudentDTOResponse deleteStudent(Long id);

    StudentDTOResponse updateStudent(StudentDTORequest studentDTORequest);

    boolean clearBase();

    long getCountOfAllStudents();
}
