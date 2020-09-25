package ru.geekbrains.markethomework.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.repositories.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Product> findProductsMinPrice(int minPrice, int page, int size) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice, PageRequest.of(page, size));
    }

    public Page<Product> findProductsMaxPrice(int maxPrice, int page, int size) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice, PageRequest.of(page, size));
    }

    public Page<Product> findProductsMinAndMaxPrice(int minPrice, int maxPrice, int page, int size) {
        return productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice, PageRequest.of(page, size));
    }
}
