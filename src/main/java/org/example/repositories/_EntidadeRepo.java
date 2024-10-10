package org.example.repositories;

import java.util.List;

public interface _EntidadeRepo<T> {
    T findById(int id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(int id);
}