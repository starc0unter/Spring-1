package com.chentsov.hw4.controllers;

import com.chentsov.hw4.entities.Product;
import com.chentsov.hw4.services.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    private ProductPageService productPageService;

    @Autowired
    public void setProductPageService(ProductPageService productPageService) {
        this.productPageService = productPageService;
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
            selectedProducts = productPageService.getProductsWithPriceBetween(min, max, pageable);
        else if (min != null) selectedProducts = productPageService.getProductsWithGreaterPrice(min, pageable);
        else if (max != null) selectedProducts = productPageService.getProductsWithLessPrice(max, pageable);
        else selectedProducts = productPageService.getPagedProducts(pageable);

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
        Product product = productPageService.getProductById(productId).orElse(null);
        if(product == null) return "redirect:/1";

        model.addAttribute("product", product);
        return "modify-product";
    }

    @PostMapping("/product/modify")
    public String modifyProduct(@ModelAttribute("product") Product product) {
        productPageService.saveOrUpdate(product);
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
        productPageService.saveOrUpdate(product);
        return "redirect:/1";
    }

}
