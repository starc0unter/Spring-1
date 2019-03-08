package com.chentsov.hw4.repositories;

import com.chentsov.hw4.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

public interface ProductsPaginationRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByPriceGreaterThan(Long price, Pageable pageable);
    Page<Product> findAllByPriceLessThan(Long target, Pageable pageable);
    Page<Product> findAllByPriceBetween(Long floor, Long ceil, Pageable pageable);
}
