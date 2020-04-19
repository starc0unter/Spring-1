package com.chentsov.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

abstract class Repository {

    private final EntityManager entityManager;

    EntityManager getEntityManager() {
        return entityManager;
    }

    Repository(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    public void shutdown() {
        entityManager.close();
    }

}
