package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.OrderDto;
import ru.geekbrains.markethomework.entities.Order;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.services.OrderService;
import ru.geekbrains.markethomework.services.UserService;
import ru.geekbrains.markethomework.utils.Cart;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orders", produces = "application/json")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;

    @GetMapping
    public List<OrderDto> showOrders(Principal principal) {
        return orderService.findAllOrdersDtoByUsername(principal.getName());
    }

    @PostMapping
    public ResponseEntity<Long> saveNewOrder(Principal principal,
                             @RequestParam(name = "address") String address,
                             @RequestParam(name = "phone") String phone
    ) {
        final User user = userService.findByUsername(principal.getName()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName()))
        );
        final Order order = new Order(user, cart, address, phone);
        final Long orderId = orderService.saveNewOrder(order).getId();
        cart.clear();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
