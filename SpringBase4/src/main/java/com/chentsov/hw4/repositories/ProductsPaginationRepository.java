package com.chentsov.hw4.repositories;

import com.chentsov.hw4.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsPaginationRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThan(Long target);
    List<Product> findAllByPriceLessThan(Long target);
    List<Product> findAllByPriceBetween(Long floor, Long ceil);
}
