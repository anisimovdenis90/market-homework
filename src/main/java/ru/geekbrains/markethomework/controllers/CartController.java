package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.CartDto;
import ru.geekbrains.markethomework.utils.Cart;

@RestController
@RequestMapping("api/v1/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    @GetMapping("/add/{product_id}")
    public void addToCart(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
    }

    @GetMapping("/dec/{product_id}")
    public void decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
    }

    @GetMapping("/remove/{product_id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
    }

    @GetMapping(produces = "application/json")
    public CartDto getCartDto() {
        cart.recalculate();
        return new CartDto(cart);
    }
}
