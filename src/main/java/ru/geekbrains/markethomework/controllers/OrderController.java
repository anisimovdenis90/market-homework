package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.markethomework.entities.Order;
import ru.geekbrains.markethomework.services.OrderService;
import ru.geekbrains.markethomework.utils.Cart;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @PostMapping("/new_order")
    public String saveNewOrder(@RequestParam(name = "name") String customerName,
                            @RequestParam(name = "phone") String customerPhone,
                            @RequestParam(name = "address") String customerAddress
    ) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setCustomerPhone(customerPhone);
        order.setCustomerAddress(customerAddress);
        cart.getItems().forEach(oi -> oi.setOrder(order));
        order.setItems(cart.getItems());
        order.setPrice(cart.getPrice());
        orderService.saveNewOrder(order);
        cart.getItems().clear();
        return "redirect:/orders";
    }
}
