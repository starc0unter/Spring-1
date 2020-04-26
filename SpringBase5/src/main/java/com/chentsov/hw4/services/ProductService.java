package com.chentsov.hw4.services;

import com.chentsov.hw4.entities.Product;
import com.chentsov.hw4.repositories.ProductsPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    private ProductsPaginationRepository productRepository;

    @Autowired
    public void setProductRepository(@NonNull ProductsPaginationRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(@NonNull Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Product> getPagedProducts(@NonNull Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsWithGreaterPrice(@NonNull Long min, @NonNull Pageable pageable) {
        return productRepository.findAllByPriceGreaterThanEqual(min, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsWithLessPrice(@NonNull Long max, @NonNull Pageable pageable) {
        return productRepository.findAllByPriceLessThanEqual(max, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsWithPriceBetween(@NonNull Long min, @NonNull Long max, @NonNull Pageable pageable) {
        return productRepository.findAllByPriceBetween(min, max, pageable);
    }

    @Transactional
    public Product saveOrUpdate(@NonNull Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Page<Product> findAllByPriceBetween(@Nullable Long minPrice, @Nullable Long maxPrice, @NonNull Pageable pageable) {
        if (minPrice != null && maxPrice != null) {
            return productRepository.findAllByPriceBetween(minPrice, maxPrice, pageable);
        }
        if (minPrice != null) {
            return productRepository.findAllByPriceGreaterThanEqual(minPrice, pageable);
        }
        if (maxPrice != null) {
            return productRepository.findAllByPriceLessThanEqual(maxPrice, pageable);
        }
        return productRepository.findAll(pageable);
    }

}
