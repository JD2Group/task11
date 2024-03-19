package it.academy.service;

import it.academy.dto.StudentDTO;

import java.util.List;

public interface AdminServise {

    List<StudentDTO> getAllStudents() throws Exception;

    StudentDTO getById(Long id) throws Exception;

    List<StudentDTO> getAllStudents(int page, int count) throws Exception;

    void createStudent(StudentDTO studentDTO) throws Exception;

    void deleteStudent(Long id) throws Exception;

    void updateStudent(StudentDTO studentDTO) throws Exception;

    void clearBase() throws Exception;

    long getCountOfAllStudents() throws Exception;
}
