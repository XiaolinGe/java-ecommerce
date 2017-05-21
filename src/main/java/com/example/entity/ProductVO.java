package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductVO {
    private  Long id;
    private  String name;

    private  Long quantityInCart;
}
