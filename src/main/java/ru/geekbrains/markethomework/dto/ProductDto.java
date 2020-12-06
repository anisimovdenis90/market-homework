package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.markethomework.entities.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotBlank(message = "Name of created product can't be empty")
    private String title;
    @Positive(message = "Price must be greater than zero")
    private int price;
    @NotBlank(message = "Category name of created product can't be empty")
    private String categoryTitle;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.categoryTitle = p.getCategory().getTitle();
    }
}
