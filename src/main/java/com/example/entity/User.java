package com.example.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    private Integer age;

    private String gender;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

}
