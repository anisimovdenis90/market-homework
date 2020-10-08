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

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showOrders(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/create")
    public String showOrderPage(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "create_order";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String saveNewOrder(Principal principal,
                              @RequestParam(name = "receiver_name") String receiverName,
                              @RequestParam(name = "phone_number") String phone,
                              @RequestParam(name = "address") String address
                              ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address);
        orderService.saveNewOrder(order);
        return "Ваш заказ #" + order.getId();
    }
}
