package it.academy.dao;

import it.academy.pojo.Student;

import java.util.List;

public interface StudentDao extends Dao<Student, Long> {

    List<Student> getAll(int page, int count);
}
