package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double price;

    private Long amount;

    @ManyToMany
    @JoinTable(
            name = "product_orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Orders> orders;

}
