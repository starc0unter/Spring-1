package com.chentsov.hw4.repositories;

import com.chentsov.hw4.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductsPaginationRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThan(Long target);
    List<Product> findAllByPriceLessThan(Long target);
    List<Product> findAllByPriceBetween(Long floor, Long ceil);
}
