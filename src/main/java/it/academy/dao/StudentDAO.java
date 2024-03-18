package it.academy.dao;

import it.academy.entities.Student;

import java.util.List;
import java.util.Map;

public interface StudentDAO extends DAO<Student, Long> {

    Student getByParameters(String parameter);
}
