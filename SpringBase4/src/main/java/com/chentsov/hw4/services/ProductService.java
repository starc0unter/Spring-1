package com.chentsov.hw4.services;

import com.chentsov.hw4.entities.Product;
import com.chentsov.hw4.repositories.ProductsPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private ProductsPaginationRepository productRepository;

    @Autowired
    public void setProductRepository(ProductsPaginationRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(int pageNumber, int pagesAmount) {
        Page<Product> productsPage = productRepository.findAll(PageRequest.of(pageNumber, pagesAmount));
        return productsPage.getContent();
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsWithGreaterPrice(Long price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsWithLessPrice(Long price) {
        return productRepository.findAllByPriceLessThan(price);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsWithPriceBetween(Long floor, Long ceil) {
        return productRepository.findAllByPriceBetween(floor, ceil);
    }

}
