package it.academy.dao.impl;

import it.academy.dao.StudentDAO;
import it.academy.models.Student;

import java.util.List;

import static it.academy.util.Constants.SELECT_ALL_FROM_STUDENT_WITH_ORDER;

public class StudentDAOImpl extends DAOImpl<Student, Long> implements StudentDAO {

    private static StudentDAOImpl studentDaoImpl;

    public static StudentDAOImpl getInstance() {

        if (studentDaoImpl == null) {
            studentDaoImpl = new StudentDAOImpl();
        }
        return studentDaoImpl;
    }

    private StudentDAOImpl() {

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
