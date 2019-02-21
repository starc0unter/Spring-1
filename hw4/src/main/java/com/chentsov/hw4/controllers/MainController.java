package com.chentsov.hw4.controllers;

import com.chentsov.hw4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{pageno}")
    public String showMain(@PathVariable("pageno") int pageNumber, Model model){
        model.addAttribute("products", productService.getAllProducts(pageNumber, 5));
        model.addAttribute("currentPageNumber", pageNumber);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, params = "max")
    public String showLess(@RequestParam("max") long max, Model model){
        model.addAttribute("products", productService.getProductsWithLessPrice(max));
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, params = "min")
    public String showMore(@RequestParam("min") long min, Model model){
        model.addAttribute("products", productService.getProductsWithGreaterPrice(min));
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, params = "min, max")
    public String showBetween(@RequestParam("min") long min, @RequestParam("max") long max, Model model){
        model.addAttribute("products", productService.getProductsWithPriceBetween(min, max));
        return "index";
    }

}
