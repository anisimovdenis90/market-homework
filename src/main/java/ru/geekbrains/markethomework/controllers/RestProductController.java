package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.services.ProductService;
import ru.geekbrains.markethomework.utils.ProductFilter;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam Map<String, String> params) {
        ProductFilter productFilter = new ProductFilter(params);
        return productService.findAllProducts(productFilter.getSpec(), 0, 10).getContent();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveProduct(p);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveProduct(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
