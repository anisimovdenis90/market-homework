package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.markethomework.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private int price;
    private String address;
    private String phone;
    private String username;
    private List<OrderItemDto> items;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.price = order.getPrice();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.username = order.getUser().getUsername();
        items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
