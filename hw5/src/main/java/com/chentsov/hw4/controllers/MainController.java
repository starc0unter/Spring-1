package com.chentsov.hw4.controllers;

import com.chentsov.hw4.entities.Product;
import com.chentsov.hw4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "{pageNumber}"})
    public String showMain(Model model,
                           @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
                           @RequestParam(value = "min", required = false) Long min,
                           @RequestParam(value = "max", required = false) Long max) {
        if (pageNumber == null) pageNumber = 1;
        PageRequest pageable = PageRequest.of(pageNumber - 1, 5);

        Page<Product> selectedProducts;
        if (min != null && max != null)
            selectedProducts = productService.getProductsWithPriceBetween(min, max, pageable);
        else if (min != null) selectedProducts = productService.getProductsWithGreaterPrice(min, pageable);
        else if (max != null) selectedProducts = productService.getProductsWithLessPrice(max, pageable);
        else selectedProducts = productService.getPagedProducts(pageable);

        int totalPages = selectedProducts.getTotalPages();

        model.addAttribute("min", min); //todo
        model.addAttribute("max", max);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("products", selectedProducts.getContent());
        return "index";
    }

    @GetMapping("/product/modify")
    public String modifyProduct(Model model, @RequestParam(value = "id") Long productId) {
        Product product = productService.getProductById(productId).orElse(null);
        if(product == null) return "redirect:/1";

        model.addAttribute("product", product);
        return "modify-product";
    }

    @PostMapping("/product/modify")
    public String modifyProduct(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/1";
    }

    @GetMapping("/product/add")
    public String addProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-product";
    }

    @PostMapping(value = {"/product/add"})
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/1";
    }

}
