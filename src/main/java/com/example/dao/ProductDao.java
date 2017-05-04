package com.example.dao;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDao extends JpaRepository<Product, Long> {


}
