package com.chentsov.persist;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final AtomicLong identity = new AtomicLong(0);
    
    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
    }

    public void insert(Product product) {
        product.setId(identity.incrementAndGet());
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
