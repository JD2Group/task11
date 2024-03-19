package it.academy.services;

import it.academy.dto.StudentDTO;
import it.academy.entities.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    List<StudentDTO> getStudentsForPage(int pageNumber, int listSize);

    boolean saveOrUpdateStudent(StudentDTO studentDTO);

    StudentDTO findStudent(Long id);

    List<StudentDTO> findStudentsByParameter(String parameter, String filter);

    boolean deleteStudent(Long id);

    int getMaxPageNumber();

}
