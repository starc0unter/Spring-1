package com.chentsov.hw4.repositories;

import com.chentsov.hw4.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface ProductsPaginationRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findAllByPriceGreaterThanEqual(@NonNull Long price, @NonNull Pageable pageable);

    Page<Product> findAllByPriceLessThanEqual(@NonNull Long target, @NonNull Pageable pageable);

    Page<Product> findAllByPriceBetween(@NonNull Long floor, @NonNull Long ceil, @NonNull Pageable pageable);

}
