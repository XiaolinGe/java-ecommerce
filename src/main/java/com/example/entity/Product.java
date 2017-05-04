package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)

public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double price;

    private Long quantity;

    @ManyToMany

    @JoinTable(
            name = "product_orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<ShoppingOrder> shoppingOrders;

}
