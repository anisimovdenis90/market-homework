package ru.geekbrains.markethomework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.markethomework.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
