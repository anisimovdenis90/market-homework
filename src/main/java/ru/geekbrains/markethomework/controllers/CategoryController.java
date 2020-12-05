package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.markethomework.dto.CategoryDto;
import ru.geekbrains.markethomework.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
