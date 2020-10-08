package ru.geekbrains.markethomework.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.entities.Order;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order saveNewOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findAllByUserId(user.getId());
    }
}
