package it.academy.dao;

import it.academy.models.Student;

import java.util.List;

public interface StudentDAO extends DAO<Student, Long> {

    List<Student> readAll(int page, int count);
}
