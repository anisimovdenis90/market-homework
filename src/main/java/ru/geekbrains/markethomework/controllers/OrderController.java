package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.configs.JwtTokenUtil;
import ru.geekbrains.markethomework.dto.OrderDto;
import ru.geekbrains.markethomework.entities.Order;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.services.OrderService;
import ru.geekbrains.markethomework.services.UserService;
import ru.geekbrains.markethomework.utils.Cart;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping(produces = "application/json")
    public List<OrderDto> showOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Order> orders = orderService.findOrdersByUser(user);
        List<OrderDto> ordersDto = orders.stream().map(OrderDto::new).collect(Collectors.toList());
        return ordersDto;
    }

    @PostMapping
    public void saveNewOrder(Principal principal,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "phone") String phone
                              ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address, phone);
        orderService.saveNewOrder(order);
    }
}
