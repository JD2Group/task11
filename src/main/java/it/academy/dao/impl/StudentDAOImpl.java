package it.academy.dao.impl;

import it.academy.dao.StudentDAO;
import it.academy.entities.Student;

import javax.persistence.criteria.*;
import java.util.List;

import static it.academy.utils.Constants.*;

public class StudentDAOImpl extends DAOImpl<Student, Long> implements StudentDAO {

    @Override
    protected Class<Student> getClazz() {
        return Student.class;
    }

    @Override
    public List<Student> getByParameter(String parameter, String filter) {
        CriteriaQuery<Student> findByParameter = criteriaBuilder().createQuery(Student.class);
        Root<Student> root = findByParameter.from(Student.class);

        parameter = "%" + parameter + "%";

        Predicate likeParameter = criteriaBuilder().disjunction();
        switch (filter) {
            case STUDENT_NAME:
                likeParameter.getExpressions().add(criteriaBuilder().like(root.get(STUDENT_NAME), parameter));
                break;
            case STUDENT_SURNAME:
                likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(STUDENT_SURNAME), parameter)));
                break;
            case STUDENT_AGE:
                likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(STUDENT_AGE).as(String.class), parameter)));
                break;
            case STUDENT_MARK:
                likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(STUDENT_MARK).as(String.class), parameter)));
                break;
            case STUDENT_ADDRESS:
                likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(STUDENT_ADDRESS).get(STUDENT_CITY), parameter)));
                likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(STUDENT_ADDRESS).get(STUDENT_STREET), parameter)));
                likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get(STUDENT_ADDRESS).get(STUDENT_HOUSE).as(String.class), parameter)));

        }

        findByParameter.select(root).where(likeParameter)
                .orderBy(criteriaBuilder().asc(root.get(STUDENT_SURNAME)));

        return entityManager().createQuery(findByParameter).getResultList();
    }
}
