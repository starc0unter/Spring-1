package com.chentsov.controller;

import com.chentsov.persist.Product;
import com.chentsov.persist.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("products", productRepository.getProducts());
        return "products";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("createProduct", new Product());
        return "createProduct";
    }

    @PostMapping
    public String storeProduct(Product product) {
        productRepository.insert(product);
        return "redirect:/product";
    }
}
