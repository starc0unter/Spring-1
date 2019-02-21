package com.chentsov.hw4.controllers;

import com.chentsov.hw4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{pageno}")
    public String showMain(@PathVariable("pageno") int pageNumber, Model model){
        model.addAttribute("products", productService.getAllProducts(pageNumber, 5));
        return "index";
    }

    @GetMapping("/min/{price}")
    public String showLess(@PathVariable("price") long price, Model model){
        model.addAttribute("products", productService.getProductsWithLessPrice(price));
        return "index";
    }

    @GetMapping("/max/{price}")
    public String showMore(@PathVariable("price") long price, Model model){
        model.addAttribute("products", productService.getProductsWithGreaterPrice(price));
        return "index";
    }

    @GetMapping("/between/{floor}_{ceil}")
    public String showBetween(@PathVariable("floor") long floor, @PathVariable("ceil") long ceil, Model model){
        model.addAttribute("products", productService.getProductsWithPriceBetween(floor, ceil));
        return "index";
    }

}
