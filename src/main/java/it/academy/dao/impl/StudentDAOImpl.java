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

        //TODO Сделать проверку по числам и строкам
        Predicate likeParameter = criteriaBuilder().disjunction();
//        likeParameter.getExpressions().add(criteriaBuilder().like(root.get("id"), parameter));
        likeParameter.getExpressions().add(criteriaBuilder().like(root.get("name"), parameter));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("surname"), parameter)));
//        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("age"), parameter)));
//        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("mark"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("address").get("city"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("address").get("street"), parameter)));
//        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("address").get("building"), parameter)));

        findByParameter.select(root).where(likeParameter);

        return entityManager().createQuery(findByParameter).getResultList();
    }

}
