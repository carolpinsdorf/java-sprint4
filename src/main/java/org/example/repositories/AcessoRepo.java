package org.example.repositories;

import org.example.entities.Acesso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class AcessoRepo implements _EntidadeRepo<Acesso> {
    private EntityManager entityManager;

    public AcessoRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Acesso findById(int id) {
        return entityManager.find(Acesso.class, id);
    }

    @Override
    public List<Acesso> findAll() {
        return entityManager.createQuery("SELECT a FROM Acesso a", Acesso.class).getResultList();
    }

    @Override
    public void save(Acesso entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Acesso entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Acesso acesso = findById(id);
            if (acesso != null) {
                entityManager.remove(acesso);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
