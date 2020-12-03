package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.PageDto;
import ru.geekbrains.markethomework.dto.ProductDto;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.exceptions.ResourceNotFoundException;
import ru.geekbrains.markethomework.services.ProductService;
import ru.geekbrains.markethomework.utils.ProductFilter;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(produces = "application/json") // /api/v1/products
    public PageDto<ProductDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                              @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> content = productService.findAllProducts(productFilter.getSpec(), page - 1, 5);
        Page<ProductDto> productsDto = new PageImpl<>(content.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());
        return new PageDto<>(productsDto);
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
