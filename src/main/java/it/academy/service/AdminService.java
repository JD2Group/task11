package it.academy.service;

import it.academy.dto.StudentDTO;

import java.util.List;

public interface AdminService {

    List<StudentDTO> getAllStudents();

    List<StudentDTO> getAllStudents(int page, int count);

    boolean createStudent(StudentDTO studentDTO);

    boolean deleteStudent(Long id);

    boolean updateStudent(StudentDTO studentDTO);

    boolean clearBase();

    long getCountOfAllStudents();
}
