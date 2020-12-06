package ru.geekbrains.markethomework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.dto.OrderDto;
import ru.geekbrains.markethomework.entities.Order;
import ru.geekbrains.markethomework.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order saveNewOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<OrderDto> findAllOrdersDtoByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public List<Order> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
