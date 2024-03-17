package it.academy.dao.impl;

import it.academy.dao.DAO;
import it.academy.util.Constants;
import it.academy.util.TransactionHelper;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static it.academy.util.Constants.*;


public class DAOImpl<T, R> implements DAO<T, R> {


    private final Class<T> clazz;
    protected TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();

    protected DAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> getAll() {

        return getEm().createQuery(getAllQuery(), clazz).getResultList();
    }

    @Override
    public T read(R id) throws EntityNotFoundException {

        return transactionHelper.find(clazz, id);
    }

    @Override
    public T update(T object) {

        return getEm().merge(object);
    }

    @Override
    public boolean delete(R id) {
        transactionHelper.begin();
        try {
            T obj = transactionHelper.find(getClazz(), id);
            if (obj == null) {
                System.out.println(Constants.NULL_EXCEPTION_MESSAGE);
                return false;
            }
            transactionHelper.remove(obj);
            obj = transactionHelper.find(getClazz(), id);
            transactionHelper.commit();
            return obj == null;

        } catch (Exception e) {
            e.printStackTrace();
            transactionHelper.rollback();
            return false;
        }

        Object rootEntity = transactionHelper.entityManager().getReference(clazz, id);
        getEm().remove(rootEntity);
    }

    @Override
    public T create(T obj) {
        assert obj != null : Constants.NULL_EXCEPTION_MESSAGE;
        transactionHelper.persist(obj);
        return obj;
    }

    @Override
    public long countOfEntitiesInBase() {

        String countQuery = String.format(SELECT_COUNT_FROM_TABLE, clazz.getSimpleName());
        return getEm().createQuery(countQuery, Long.class).getSingleResult();
    }

    @Override
    public void clearTable() {

        String deleteQuery = String.format(DELETE_ALL_FROM_TABLE, clazz.getSimpleName());
        getEm().createQuery(deleteQuery).executeUpdate();
    }


    protected String getAllQuery() {

        return String.format(SELECT_ALL_FROM_TABLE, clazz.getSimpleName());
    }
}
