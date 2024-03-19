package it.academy.dao;

import it.academy.entities.Student;

import java.util.List;
import java.util.Map;

public interface StudentDAO extends DAO<Student, Long> {

    List<Student> getByParameter(String parameter, String filter);
}
