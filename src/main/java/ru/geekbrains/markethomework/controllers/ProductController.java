package ru.geekbrains.markethomework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.services.ProductService;


@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    private final String minValue = "" + Integer.MIN_VALUE;
    private final String maxValue = "" + Integer.MAX_VALUE;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProducts(Model model, @RequestParam(defaultValue = minValue, name = "min") Integer minPrice, @RequestParam(defaultValue = maxValue, name = "max") Integer maxPrice, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        if (minPrice.toString().equals(minValue) && maxPrice.toString().equals(maxValue)) {
            model.addAttribute("products", productService.findAll(page - 1, 5));
        } else if (!minPrice.toString().equals(minValue) && maxPrice.toString().equals(maxValue)) {
            model.addAttribute("products", productService.findAllByPriceGreaterThanEqual(minPrice, page - 1, 5));
        } else if (minPrice.toString().equals(minValue)) {
            model.addAttribute("products", productService.findAllByPriceLessThanEqual(maxPrice, page - 1, 5));
        } else {
            model.addAttribute("products", productService.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice, page - 1, 5));
        }
        return "products";
    }
}
