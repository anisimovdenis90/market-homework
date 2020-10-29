package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.entities.Order;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.services.OrderService;
import ru.geekbrains.markethomework.services.UserService;
import ru.geekbrains.markethomework.utils.Cart;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping(produces = "application/json")
    public List<Order> showOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Order> orders = orderService.findOrdersByUser(user);
        return orders;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Order saveNewOrder(@RequestParam(name = "username") String receiverName,
                              @RequestParam(name = "telephone") String telephone,
                              @RequestParam(name = "address") String address
                              ) {
        System.out.println(receiverName);
        System.out.println(address);
        User user = userService.findByUsername(receiverName);
        Order order = new Order(user, cart, address);
        orderService.saveNewOrder(order);
        return order;
    }
}
