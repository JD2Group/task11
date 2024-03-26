package it.academy.dao.impl;

import it.academy.dao.StudentDao;
import it.academy.pojo.Student;

import java.util.List;

import static it.academy.util.Constants.*;

public class StudentDaoImpl extends DaoImpl<Student, Long> implements StudentDao {

    private static StudentDaoImpl studentDaoImpl;

    private StudentDaoImpl() {

        super(Student.class);
    }

    public static StudentDaoImpl getInstance() {

        if (studentDaoImpl == null) {
            studentDaoImpl = new StudentDaoImpl();
        }
        return studentDaoImpl;
    }

    @Override
    public List<Student> getAll(int page, int count) {

        return getEm().createQuery(SELECT_ALL_FROM_STUDENT_WITH_ORDER, Student.class)
                   .setMaxResults(count)
                   .setFirstResult((page - 1) * count)
                   .getResultList();
    }

    @Override
    public long countOfStudentsFrmCountry(Long countryId) {

        String countQuery = String.format(SELECT_COUNT_STUDENT_FROM_COUNTRY_BY_CONTRYID, countryId);
        return getEm().createQuery(countQuery, Long.class).getSingleResult();
    }

    @Override
    public List<Student> getAllFromCountry(int page, int count, Long countryId) {

        return getEm()
                   .createQuery(
                       String.format(SELECT_S_FROM_STUDENT_BY_COUNTRY_ID, countryId),
                       Student.class)
                   .setMaxResults(count)
                   .setFirstResult((page - 1) * count)
                   .getResultList();
    }

    @Override
    protected String getAllQuery() {

        return SELECT_ALL_FROM_STUDENT_WITH_ORDER;
    }
}
