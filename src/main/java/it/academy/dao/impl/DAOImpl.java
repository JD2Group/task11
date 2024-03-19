package it.academy.dao.impl;

import it.academy.dao.DAO;
import it.academy.entities.Student;
import it.academy.utils.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static it.academy.utils.Constants.STUDENT_ID;

public abstract class DAOImpl<T extends Serializable, R> implements DAO<T, R> {
    private TransactionManager transactionManager = TransactionManager.getInstance();


    protected abstract Class<T> getClazz();

    @Override
    public T save(T object) {
        entityManager().persist(object);
        return object;
    }

    @Override
    public T update(T object) {
        entityManager().merge(object);
        return object;
    }

    @Override
    public T get(R id) {
        return entityManager().find(getClazz(), id);
    }

    @Override
    public boolean delete(R id) {
        entityManager();
        T object = entityManager().find(getClazz(), id);
        if (object != null) {
            entityManager().remove(object);
        }
        return entityManager().find(getClazz(), id) == null;
    }

    @Override
    public List<T> getAll() {
        CriteriaQuery<T> getAll = criteriaBuilder().createQuery(getClazz());
        Root<T> root = getAll.from(getClazz());
        getAll.select(root);

        return entityManager().createQuery(getAll).getResultList();
    }

    @Override
    public List<T> getList(int pageNumber, int numberOfRecords) {
        CriteriaQuery<T> getList = criteriaBuilder().createQuery(getClazz());
        Root<T> root = getList.from(getClazz());

        getList.select(root)
                .orderBy(criteriaBuilder().desc(root.get(STUDENT_ID)));

        return entityManager().createQuery(getList)
                .setFirstResult(((pageNumber - 1) * numberOfRecords))
                .setMaxResults(numberOfRecords)
                .getResultList();
    }

    @Override
    public long getNumberOfEntries() {
        CriteriaQuery<Long> getTableSize = criteriaBuilder().createQuery(Long.class);
        getTableSize.select(criteriaBuilder().count(getTableSize.from(getClazz())));
        return entityManager().createQuery(getTableSize).getSingleResult();
    }

    protected EntityManager entityManager() {
        return transactionManager.getEntityManager();
    }

    protected CriteriaBuilder criteriaBuilder() {
        return transactionManager.getCriteriaBuilder();
    }
}
