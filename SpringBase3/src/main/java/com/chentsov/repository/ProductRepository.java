package com.chentsov.repository;

import com.chentsov.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public final class ProductRepository extends Repository {

    public ProductRepository(EntityManagerFactory factory) {
        super(factory);
    }

    public Product removeProduct(long productId) {
        final EntityManager em = getEntityManager();
        em.getTransaction().begin();
        final Product product = em.find(Product.class, productId);
        em.remove(product);
        em.getTransaction().commit();
        return product;
    }
}
