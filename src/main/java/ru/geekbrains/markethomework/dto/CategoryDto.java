package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.markethomework.entities.Category;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;

    @Size(min = 3, max = 255, message = "Wrong category name, at least 3 symbols")
    private String title;

    public CategoryDto(Category category) {
        this.categoryId = category.getId();
        this.title = category.getTitle();
    }
}
