package it.academy.dao;

import it.academy.pojo.Student;

import java.util.List;

public interface StudentDao extends Dao<Student, Long> {

    List<Student> getAll(int page, int count);

    long countOfStudentsFrmCountry(Long countryId);

    List<Student> getAllFromCountry(int page, int count, Long countryId);
}
