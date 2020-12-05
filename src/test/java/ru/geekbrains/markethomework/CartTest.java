package ru.geekbrains.markethomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.markethomework.entities.Product;
import ru.geekbrains.markethomework.services.ProductService;
import ru.geekbrains.markethomework.utils.Cart;

import java.util.Optional;

@SpringBootTest(classes = Cart.class)
public class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void init() {
        Long productId = 1L;
        int price = 100;

        Product product = new Product();
        product.setId(productId);
        product.setPrice(price);
        product.setTitle("Product #" + productId);

        Mockito.doReturn(Optional.of(product))
                .when(productService)
                .findProductById(productId);
    }

    @Test
    public void cartAddTest() {
        cart.addOrIncrement(1L);
        Assertions.assertEquals(1, cart.getItems().size());
        Assertions.assertEquals(100, cart.getPrice());

        cart.addOrIncrement(1L);
        cart.addOrIncrement(1L);
        Assertions.assertEquals(1, cart.getItems().size());
        Assertions.assertEquals(100 * 3, cart.getPrice());
    }

    @Test
    public void cartDeleteTest() {
        cart.addOrIncrement(1L);
        cart.addOrIncrement(1L);
        cart.addOrIncrement(1L);
        cart.decrementOrRemove(1L);
        Assertions.assertEquals(1, cart.getItems().size());
        Assertions.assertEquals(100 * 2, cart.getPrice());
    }

    @Test
    public void cartRemoveTest() {
        cart.addOrIncrement(1L);
        cart.addOrIncrement(1L);
        cart.remove(1L);
        Assertions.assertEquals(0, cart.getItems().size());
        Assertions.assertEquals(0, cart.getPrice());
    }

    @Test
    public void cartClearTest() {
        cart.addOrIncrement(1L);
        cart.addOrIncrement(1L);
        cart.clear();
        Assertions.assertEquals(0, cart.getItems().size());
        Assertions.assertEquals(0, cart.getPrice());
    }
}