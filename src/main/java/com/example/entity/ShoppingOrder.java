package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class ShoppingOrder {
    @Id
    @GeneratedValue

    private Long id;

    private Double price;

    private Date date;

    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "shoppingOrders")
    private List<Product> products;

}
