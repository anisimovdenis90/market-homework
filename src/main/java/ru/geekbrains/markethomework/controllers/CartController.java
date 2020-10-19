package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.exceptions.ResourceNotFoundException;
import ru.geekbrains.markethomework.services.ProductService;
import ru.geekbrains.markethomework.utils.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller
//@RequestMapping("/cart")
//@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private Cart cart;

    @GetMapping
    public String showCartPage() {
        return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart (
            @PathVariable(name = "product_id") Long productId,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        cart.addOrIncrement(productId);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/inc/{product_id}")
    public String addOrIncrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
        return "redirect:/cart";
    }

    @GetMapping("/dec/{product_id}")
    public String decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{product_id}")
    public String removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/order")
    public String showCreateOrderForm() {
        return "create_order";
    }
}
