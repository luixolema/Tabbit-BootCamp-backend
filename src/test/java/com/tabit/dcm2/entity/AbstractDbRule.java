package com.tabit.dcm2.entity;

import org.junit.rules.ExternalResource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import static java.lang.String.format;

public abstract class AbstractDbRule<ENTITY extends IEntity> extends ExternalResource {

    private EntityManager entityManager;

    @Override
    protected void before() {
        delete();
    }

    @Override
    protected void after() {
        entityManager.clear();
    }

    private void delete() {
        entityManager.getTransaction().begin();
        entityManager.createQuery(format("delete from %s", getEntitySimpleName())).executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void persist(List<ENTITY> entities) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        for (ENTITY entity : entities) {
            entityManager.persist(entity);
        }
        transaction.commit();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected abstract String getEntitySimpleName();

}
