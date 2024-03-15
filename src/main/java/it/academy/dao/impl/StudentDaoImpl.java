package it.academy.dao.impl;

import it.academy.dao.StudentDao;
import it.academy.pojo.Student;

import java.util.List;

import static it.academy.util.Constants.SELECT_ALL_FROM_STUDENT_WITH_ORDER;

public class StudentDaoImpl extends DaoImpl<Student, Long> implements StudentDao {

    private static StudentDaoImpl studentDaoImpl;

    public static StudentDaoImpl getInstance() {

        if (studentDaoImpl == null) {
            studentDaoImpl = new StudentDaoImpl();
        }
        return studentDaoImpl;
    }

    private StudentDaoImpl() {

        super(Student.class);
    }

    @Override
    public List<Student> getAll(int page, int count) {

        return getEm().createQuery(SELECT_ALL_FROM_STUDENT_WITH_ORDER, Student.class)
                   .setMaxResults(count)
                   .setFirstResult((page - 1) * count)
                   .getResultList();
    }

    @Override
    protected String getAllQuery() {

        return SELECT_ALL_FROM_STUDENT_WITH_ORDER;
    }
}
