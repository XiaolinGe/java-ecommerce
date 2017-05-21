package com.example.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
public class User implements Serializable {
    @Id
    @GeneratedValue

    private Long id;

    private String name;

    private String password;

    private Integer age;

    private String gender;

    private String phone;

    private String email;

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingOrder> shoppingOrders;

}
