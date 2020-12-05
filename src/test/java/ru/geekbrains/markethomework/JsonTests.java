package ru.geekbrains.markethomework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.geekbrains.markethomework.entities.Product;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Product> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("TEST");
        product.setPrice(100);

        assertThat(jackson.write(product)).hasJsonPathNumberValue("$.id")
                .hasJsonPathNumberValue("$.price")
                .hasJsonPathStringValue("$.title")
                .extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(jackson.write(product)).extractingJsonPathStringValue("$.title").isEqualTo("TEST");
        assertThat(jackson.write(product)).extractingJsonPathNumberValue("$.price").isEqualTo(100);
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"title\":\"PROD\", \"price\": 100}";
        Product product = new Product();
        product.setId(2L);
        product.setTitle("PROD");
        product.setPrice(100);

        assertThat(jackson.parse(content)).isEqualTo(product);
        assertThat(jackson.parseObject(content).getPrice()).isEqualTo(100);
    }
}