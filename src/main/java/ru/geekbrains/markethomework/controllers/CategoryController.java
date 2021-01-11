package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.CategoryDto;
import ru.geekbrains.markethomework.exceptions.InputDataError;
import ru.geekbrains.markethomework.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAllCategoriesDto();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveCategory(@RequestBody @Validated CategoryDto c, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new InputDataError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }
        if (categoryService.isPresent(c.getTitle())) {
            return new ResponseEntity<>(new InputDataError("Category " + c.getTitle() + " already exists"), HttpStatus.BAD_REQUEST);
        }
        categoryService.saveCategoryFromCategoryDto(c);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
