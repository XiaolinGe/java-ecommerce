package com.example.dao;

import com.example.entity.ShoppingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingOrdersDao extends JpaRepository<ShoppingOrder, Long> {
}
