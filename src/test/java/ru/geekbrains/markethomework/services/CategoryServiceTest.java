package ru.geekbrains.markethomework.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.markethomework.dto.CategoryDto;
import ru.geekbrains.markethomework.entities.Category;
import ru.geekbrains.markethomework.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = CategoryService.class)
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @MockBean
    CategoryRepository categoryRepository;

    @Test
    public void findAllCategoriesTest() {
        Category categoryInDB = new Category();
        categoryInDB.setTitle("New Category");

        Mockito.doReturn(List.of(categoryInDB))
                .when(categoryRepository)
                .findAll();

        List<Category> categories = categoryService.findAllCategories();
        Assertions.assertNotNull(categories);
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals(categoryInDB.getTitle(), categories.get(0).getTitle());
        Mockito.verify(categoryRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findCategoryByName() {
        Category categoryInDB = new Category();
        categoryInDB.setTitle("New Category");

        Mockito.doReturn(Optional.of(categoryInDB))
                .when(categoryRepository)
                .findCategoryByTitle("New Category");

        Category category = categoryService.findCategoryByName("New Category");
        Assertions.assertNotNull(category);
        Assertions.assertEquals(categoryInDB.getTitle(), category.getTitle());
        Mockito.verify(categoryRepository, Mockito.times(1)).findCategoryByTitle("New Category");
    }

    @Test
    public void saveCategoryFromCategoryDtoTest() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle("New Category");
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());

        categoryService.saveCategoryFromCategoryDto(categoryDto);
        Mockito.verify(categoryRepository).save(category);
    }
}
