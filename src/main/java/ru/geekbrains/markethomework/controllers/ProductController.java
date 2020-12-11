package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.PageDto;
import ru.geekbrains.markethomework.dto.ProductDto;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.exceptions.InputDataError;
import ru.geekbrains.markethomework.services.ProductService;
import ru.geekbrains.markethomework.utils.ProductFilter;

import java.util.Map;

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
        return productService.findAllProductsDto(productFilter.getSpec(), page - 1, 5);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody @Validated ProductDto p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new InputDataError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        p.setId(null);
        productService.saveProductFromProductDto(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateProduct(@RequestBody Product p) {
        productService.saveProduct(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
