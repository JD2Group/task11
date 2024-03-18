package it.academy.utils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.function.Supplier;

public class TransactionManager {
    private static TransactionManager instance;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
//    private Logger log = Logger.getLogger(TransactionManager.class);

    private TransactionManager() {
        entityManager = HibernateUtil.entityManager();
    }

    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        checkEntityManager();
        return entityManager;
    }

    public void closeEntityManager() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    public CriteriaBuilder getCriteriaBuilder() {
        if (criteriaBuilder == null) {
            checkEntityManager();
            criteriaBuilder = entityManager.getCriteriaBuilder();
        }
        return criteriaBuilder;
    }

    public <T> T executeTransaction(Supplier<T> method) {
        beginTransaction();
        T result = null;

        try {
            result = method.get();
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return result;
    }

    private void beginTransaction() {
        checkEntityManager();
        commit();
        entityManager.getTransaction().begin();
    }

    private void commit() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
    }

    private void checkEntityManager() {
        if (!entityManager.isOpen()) {
            entityManager = HibernateUtil.entityManager();
        }
    }
}
