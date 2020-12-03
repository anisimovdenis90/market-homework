package ru.geekbrains.markethomework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.markethomework.dto.PageDto;
import ru.geekbrains.markethomework.dto.ProductDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @WithMockUser(username = "Bob", roles = "USER")
    public void fullRestTest() {
        PageDto<ProductDto> products = restTemplate.getForObject("/api/v1/products", PageDto.class,
                "p=1", "title=", "min_price=", "max_price=", "category_id=");
        assertThat(products).isNotNull();
        assertThat(products.getContent()).isNotEmpty();
        assertThat(products.getContent().size()).isEqualTo(5);
    }

    @Test
    @WithMockUser(username = "Bob", roles = "USER")
    public void getProductByIdServerTest() {
        ProductDto productDto = restTemplate.getForObject("/api/v1/products/1", ProductDto.class);
        assertThat(productDto).isNotNull();
        assertThat(productDto.getId()).isEqualTo(1);
    }
}
