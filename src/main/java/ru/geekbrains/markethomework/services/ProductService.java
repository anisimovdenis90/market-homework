package ru.geekbrains.markethomework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.repositories.ProductRepository;
import ru.geekbrains.markethomework.soap.ProductSoap;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAllProducts(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public List<ProductSoap> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductSoap> soapList = products.stream().map(p -> {
            ProductSoap ps = new ProductSoap();
            ps.setId(p.getId());
            ps.setPrice(p.getPrice());
            ps.setTitle(p.getTitle());
            ps.setCategoryTitle(p.getCategory().getTitle());
            return ps;
        }).collect(Collectors.toList());
        return soapList;
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
