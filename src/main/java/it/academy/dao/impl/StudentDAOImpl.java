package it.academy.dao.impl;

import it.academy.dao.StudentDAO;
import it.academy.entities.Student;

import javax.persistence.criteria.*;
import java.util.List;

public class StudentDAOImpl extends DAOImpl<Student, Long> implements StudentDAO {

    @Override
    protected Class<Student> getClazz() {
        return Student.class;
    }

    @Override
    public List<Student> getByParameter(String parameter) {
        CriteriaQuery<Student> findByParameter = criteriaBuilder().createQuery(Student.class);
        Root<Student> root = findByParameter.from(Student.class);

        parameter = "%" + parameter + "%";

        Predicate likeParameter = criteriaBuilder().disjunction();
        likeParameter.getExpressions().add(criteriaBuilder().like(root.get("name"), parameter));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("surname"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("age").as(String.class), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("mark").as(String.class), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("address").get("city"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("address").get("street"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("address").get("building").as(String.class), parameter)));

        findByParameter.select(root).where(likeParameter)
            .orderBy(criteriaBuilder().asc(root.get("surname")));

        return entityManager().createQuery(findByParameter).getResultList();
    }
}
