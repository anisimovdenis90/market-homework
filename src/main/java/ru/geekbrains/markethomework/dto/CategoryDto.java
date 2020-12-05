package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.markethomework.entities.Category;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String title;

    public CategoryDto(Category category) {
        this.categoryId = category.getId();
        this.title = category.getTitle();
    }
}
