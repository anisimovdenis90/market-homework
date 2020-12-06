package ru.geekbrains.markethomework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.dto.CategoryDto;
import ru.geekbrains.markethomework.entities.Category;
import ru.geekbrains.markethomework.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public List<CategoryDto> findAllCategoriesDto() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
