package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;

    @GetMapping(produces = "application/json")
    public List<OrderDto> showOrders(Principal principal) {
        return orderService.findAllOrdersDtoByUsername(principal.getName());
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewOrder(Principal principal,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "phone") String phone
    ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address, phone);
        orderService.saveNewOrder(order);
        cart.clear();
    }
}
