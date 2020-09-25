package ru.geekbrains.markethomework.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.markethomework.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceGreaterThanEqual(int minPrice, Pageable var1);
    Page<Product> findAllByPriceLessThanEqual(int maxPrice, Pageable var1);
    Page<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice, Pageable var1);
}
