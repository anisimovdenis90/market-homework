package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.services.ProductService;


@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showProducts(Model model, @RequestParam(required = false, name = "min") Integer minPrice, @RequestParam(required = false, name = "max") Integer maxPrice, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        if (minPrice == null && maxPrice == null) {
            model.addAttribute("products", productService.findAllProducts(page - 1, 5));
        } else if (minPrice != null && maxPrice == null) {
            model.addAttribute("products", productService.findProductsMinPrice(minPrice, page - 1, 5));
        } else if (minPrice == null) {
            model.addAttribute("products", productService.findProductsMaxPrice(maxPrice, page - 1, 5));
        } else {
            model.addAttribute("products", productService.findProductsMinAndMaxPrice(minPrice, maxPrice, page - 1, 5));
        }
        return "products";
    }
}
