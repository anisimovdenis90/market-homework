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

        assertThat(this.jackson.write(product)).hasJsonPathNumberValue("$.id");
        assertThat(this.jackson.write(product)).hasJsonPathNumberValue("$.price");
        assertThat(this.jackson.write(product)).hasJsonPathStringValue("$.title");
        assertThat(this.jackson.write(product)).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(this.jackson.write(product)).extractingJsonPathNumberValue("$.price").isEqualTo(100);
        assertThat(this.jackson.write(product)).extractingJsonPathStringValue("$.title").isEqualTo("TEST");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"title\":\"PROD\", \"price\": 100}";
        Product product = new Product();
        product.setId(2L);
        product.setTitle("PROD");
        product.setPrice(100);

        assertThat(this.jackson.parse(content)).isEqualTo(product);
        assertThat(this.jackson.parseObject(content).getPrice()).isEqualTo(100);
    }
}