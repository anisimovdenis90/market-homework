package ru.geekbrains.markethomework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.markethomework.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByTitle(String title);
}
