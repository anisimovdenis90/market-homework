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

        String filterTitle = params.get("title");
        if (filterTitle !=null && !filterTitle.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }

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

        if (params.containsKey("category") && !params.get("category").isBlank()) {
            Long categoryId = Long.parseLong(params.get("category"));
            spec = spec.and(ProductSpecifications.categoryId(categoryId));
            filterDefinitionBuilder.append("&category=").append(categoryId);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }
}