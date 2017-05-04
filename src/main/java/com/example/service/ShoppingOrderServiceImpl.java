package com.example.service;


import com.example.dao.ProductDao;
import com.example.dao.ShoppingOrdersDao;

import com.example.entity.Product;
import com.example.entity.ShoppingOrder;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gezilin on 3/05/17.
 */

@Service
public class ShoppingOrderServiceImpl implements ShoppingOrderService {

    @Autowired
    private ShoppingOrdersDao shoppingOrdersDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public ShoppingOrder createOrder(User user, List<Long> productIds) {
        ShoppingOrder order = new ShoppingOrder();
        List<Product> products = javaslang.collection.List.ofAll(productDao.findAll()).filter(e -> productIds.contains(e.getId())).toJavaList();

        order.setUser(user);
        order.setProducts(products);

        shoppingOrdersDao.save(order);

        return order;

    }
}
