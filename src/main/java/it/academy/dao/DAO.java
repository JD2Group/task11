package it.academy.dao;


import java.io.Serializable;
import java.util.List;

public interface DAO<T extends Serializable, R> {

    T save(T object);

    T update(T object);

    T get(R id);

    boolean delete(R id);

    List<T> getAll();

    List<T> getList(int pageNumber, int numberOfRecords);

    long getNumberOfEntries();

}

