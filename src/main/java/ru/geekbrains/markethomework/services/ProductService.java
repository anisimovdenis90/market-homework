package ru.geekbrains.markethomework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.dto.PageDto;
import ru.geekbrains.markethomework.dto.ProductDto;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.repositories.ProductRepository;
import ru.geekbrains.markethomework.soap.ProductSoap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAllProducts(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public PageDto<ProductDto> findAllProductsDto(Specification<Product> spec, int page, int size) {
        Page<Product> content = productRepository.findAll(spec, PageRequest.of(page, size));
        Page<ProductDto> productsDto = new PageImpl<>(content.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());
        return new PageDto<>(productsDto);
    }

    public List<ProductSoap> findAllProductsSoap() {
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

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findProductDtoById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product saveProductFromProductDto(ProductDto p) {
        Product newProduct = new Product();
        newProduct.setTitle(p.getTitle());
        newProduct.setPrice(p.getPrice());
        newProduct.setCategory(categoryService.findCategoryByName(p.getCategoryTitle()));
        return productRepository.save(newProduct);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
