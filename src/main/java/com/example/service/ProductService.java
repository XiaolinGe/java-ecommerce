package com.example.service;

import com.example.entity.Product;

import java.util.List;

/**
 * Created by gezilin on 4/05/17.
 */
public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findOne(Long id);

    void delete(Long id);
}
