package org.example.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class _BaseRepoImpl<T> implements repositories._EntidadeRepo<T> {
    protected Connection connection;

    public _BaseRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public abstract T findById(Long id);

    @Override
    public abstract List<T> findAll();

    @Override
    public abstract void save(T entity);

    @Override
    public abstract void update(T entity);

    @Override
    public abstract void delete(Long id);
}
