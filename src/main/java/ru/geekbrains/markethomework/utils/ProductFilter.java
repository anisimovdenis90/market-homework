package ru.geekbrains.markethomework.utils;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.repositories.specifications.ProductSpecifications;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }
        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLessOrEqualThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }
        filterDefinition = filterDefinitionBuilder.toString();
    }
}