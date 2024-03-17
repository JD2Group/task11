package it.academy.dao;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface DAO<T, R> {

    List<T> getAll();

    T read(R id) throws EntityNotFoundException;

    T update(T t);

    boolean delete(R id) throws EntityNotFoundException;

    T create(T t);

    long countOfEntitiesInBase();

    void clearTable();
}
