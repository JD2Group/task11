package it.academy.dao.impl;

import it.academy.dao.DAO;
import it.academy.utils.TransactionHelper;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static it.academy.utils.Constants.*;


public class DAOImpl<T, R> implements DAO<T, R> {


    private final Class<T> clazz;
    protected TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();

    protected DAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T create(T obj) {
        return transactionHelper.persist(obj);
    }

    @Override
    public T read(R id) throws EntityNotFoundException {
        return transactionHelper.find(clazz, id);
    }

    @Override
    public List<T> readAll() {
        return transactionHelper.entityManager()
                .createQuery(String.format(SELECT_ALL_FROM_TABLE, clazz.getSimpleName()), clazz)
                .getResultList();
    }

    @Override
    public T update(T obj) {
        return transactionHelper.merge(obj);
    }

    @Override
    public boolean delete(R id) {
        T obj = transactionHelper.find(clazz, id);
        transactionHelper.remove(obj);
        obj = transactionHelper.find(clazz, id);
        return obj == null;
    }

    @Override
    public long countOfEntitiesInBase() {
        String countQuery = String.format(SELECT_COUNT_FROM_TABLE, clazz.getSimpleName());
        return transactionHelper.entityManager().createQuery(countQuery, Long.class).getSingleResult();
    }

    @Override
    public boolean clearTable() {
        String deleteQuery = String.format(DELETE_ALL_FROM_TABLE, clazz.getSimpleName());
        transactionHelper.entityManager().createQuery(deleteQuery).executeUpdate();
        return true;
    }

}
