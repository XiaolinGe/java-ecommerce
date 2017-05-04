package com.example.entity;

import com.example.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Foo {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;


    private String name;

    private Integer age;

    private String gender;
}
