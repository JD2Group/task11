package it.academy.dao.impl;

import it.academy.dao.StudentDAO;
import it.academy.entities.Address;
import it.academy.entities.Student;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

public class StudentDAOImpl extends DAOImpl<Student, Long> implements StudentDAO {

    @Override
    protected Class<Student> getClazz() {
        return Student.class;
    }

    @Override
    public Student getByParameters(String parameter) {
        CriteriaQuery<Student> findByParameter = criteriaBuilder().createQuery(Student.class);
        Root<Student> root = findByParameter.from(Student.class);
        Root<Address> addressRoot = findByParameter.from(Address.class);

        Predicate likeParameter = criteriaBuilder().conjunction();
        likeParameter.getExpressions().add(criteriaBuilder().like(root.get("id"), parameter));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("name"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("surname"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("age"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(root.get("mark"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(addressRoot.get("city"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(addressRoot.get("street"), parameter)));
        likeParameter.getExpressions().add(criteriaBuilder().or(criteriaBuilder().like(addressRoot.get("houseNumber"), parameter)));

        findByParameter.select(root).where(likeParameter);

        return entityManager().createQuery(findByParameter).getSingleResult();
    }

}
