package ru.geekbrains.markethomework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.markethomework.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
