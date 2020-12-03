package ru.geekbrains.markethomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.markethomework.entities.Category;
import ru.geekbrains.markethomework.repositories.CategoryRepository;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllCategoryRepositoryTest() {
        List<Category> categoryList = categoryRepository.findAll();
        Assertions.assertEquals(3, categoryList.size());
    }

    @Test
    public void findByIdCategoryRepositoryTest() {
        Category categoryFromDb = categoryRepository.findById(1L).get();
        Assertions.assertEquals("Продукты питания", categoryFromDb.getTitle());
    }

    @Test
    public void saveRepositoryTest() {
        Category category = new Category();
        category.setTitle("New Category");
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categoryList = categoryRepository.findAll();
        Assertions.assertEquals(4, categoryList.size());

        Assertions.assertEquals("New Category", categoryList.get(3).getTitle());
    }
}
