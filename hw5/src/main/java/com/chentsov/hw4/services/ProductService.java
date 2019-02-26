package com.chentsov.hw4.services;

import com.chentsov.hw4.entities.Product;
import com.chentsov.hw4.repositories.ProductsPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private ProductsPaginationRepository productRepository;

    @Autowired
    public void setProductRepository(ProductsPaginationRepository productRepository) {
        this.productRepository = productRepository;
    }

    //todo: is it better to unwrap Optional here or just send it up to the Controller?
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> getPagedProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsWithGreaterPrice(Long min, Pageable pageable) {
        return productRepository.findAllByPriceGreaterThan(min, pageable);
    }

    public Page<Product> getProductsWithLessPrice(Long max, Pageable pageable) {
        return productRepository.findAllByPriceLessThan(max, pageable);
    }

    public Page<Product> getProductsWithPriceBetween(Long min, Long max, Pageable pageable) {
        return productRepository.findAllByPriceBetween(min, max, pageable);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

}
