package ru.geekbrains.markethomework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import ru.geekbrains.markethomework.utils.Cart;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private int price;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    public Order(User user, Cart cart, String address, String phone) {
        this.user = user;
        this.price = cart.getPrice();
        this.address = address;
        this.phone = phone;
        this.items = new ArrayList<>();
        cart.getItems().stream().forEach(oi -> {
            oi.setOrder(this);
            items.add(oi);
        });
    }
}
