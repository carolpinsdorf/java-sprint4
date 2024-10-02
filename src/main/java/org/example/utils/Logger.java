package org.example.utils;

import org.example.entitities._EntidadeBase;

public interface Logger <T extends _EntidadeBase> {
    void logCreate(T entity);
    void logUpdate(T entity);
    void logDelete(T entity);
}
