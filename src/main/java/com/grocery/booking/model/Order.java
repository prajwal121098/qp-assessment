package com.grocery.booking.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId; // Reference to User

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
