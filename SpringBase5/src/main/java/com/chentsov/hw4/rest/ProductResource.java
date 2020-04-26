package com.chentsov.hw4.rest;

import com.chentsov.hw4.entities.Product;
import com.chentsov.hw4.rest.errorResponse.ErrorResponse;
import com.chentsov.hw4.rest.errorResponse.NotFoundException;
import com.chentsov.hw4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/id/{id}", produces = "application/json")
    public Product findById(@PathVariable("id") long id) {
        return productService.findProductById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id field found in create request");
        }
        return productService.saveOrUpdate(product);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {
        productService.saveOrUpdate(product);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException exception) {
        final ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Product not found", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse>  illegalArgumentException(IllegalArgumentException exception) {
        final ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
