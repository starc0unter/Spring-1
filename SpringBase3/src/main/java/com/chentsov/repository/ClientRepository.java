package com.chentsov.repository;

import com.chentsov.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public final class ClientRepository extends Repository {

    public ClientRepository(EntityManagerFactory factory) {
        super(factory);
    }

    public Client removeClient(long clientId) {
        final EntityManager em = getEntityManager();
        em.getTransaction().begin();
        final Client client = em.find(Client.class, clientId);
        em.detach(client);
        em.getTransaction().commit();
        return client;
    }
}
