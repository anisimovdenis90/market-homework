package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.entities.Category;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.exceptions.ResourceNotFoundException;
import ru.geekbrains.markethomework.services.CategoryService;
import ru.geekbrains.markethomework.services.ProductService;
import ru.geekbrains.markethomework.utils.ProductFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public List<Iterable<?>> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                        @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        List<Iterable<?>> list = new ArrayList<>();
        ProductFilter productFilter = new ProductFilter(params);
        List<Category> categories = categoryService.findAll();
        Page<Product> products = productService.findAllProducts(productFilter.getSpec(), page - 1, 5);
        list.add(categories);
        list.add(products);
        return list;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveProduct(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
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
