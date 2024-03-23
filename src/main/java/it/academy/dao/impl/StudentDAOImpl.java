package it.academy.dao.impl;

import it.academy.dao.StudentDAO;
import it.academy.models.Student;

import java.util.List;

import static it.academy.utils.Constants.SELECT_ALL_FROM_STUDENT_WITH_ORDER;

public class StudentDAOImpl extends DAOImpl<Student, Long> implements StudentDAO {

    public StudentDAOImpl() {
        super(Student.class);
    }

    @Override
    public Student create(Student obj) {
        return super.create(obj);
    }

    @Override
    public List<Student> readAll(int page, int count) {
        return transactionHelper.entityManager().createQuery(SELECT_ALL_FROM_STUDENT_WITH_ORDER, Student.class)
                .setMaxResults(count)
                .setFirstResult((page - 1) * count)
                .getResultList();
    }

}
