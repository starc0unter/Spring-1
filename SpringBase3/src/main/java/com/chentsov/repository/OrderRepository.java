package com.chentsov.repository;

import com.chentsov.entity.Order;
import com.chentsov.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public final class OrderRepository extends Repository {

    public OrderRepository(EntityManagerFactory factory) {
        super(factory);
    }

    public List<Order> getOrdersByProducts(long productId) {
        final EntityManager em = getEntityManager();
        return em.createQuery("from Order o join fetch o.items i where i.product.id = :productId", Order.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    public List<Order> getOrdersByClient(long clientId) {
        final EntityManager em = getEntityManager();
        return em.createQuery("from Order o where o.client.id = :clientId", Order.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

}
