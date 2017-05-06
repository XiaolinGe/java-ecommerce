package com.example.service;


import com.example.dao.ProductDao;
import com.example.dao.ShoppingOrdersDao;

import com.example.dao.UserDao;
import com.example.entity.Product;
import com.example.entity.ShoppingOrder;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private UserDao userDao;

    @Override
    public ShoppingOrder createOrder(String note, User user, List<Long> productIds) {
        ShoppingOrder order = new ShoppingOrder();
        List<Product> products = javaslang.collection.List.ofAll(productDao.findAll()).filter(e -> productIds.contains(e.getId())).toJavaList();
        Double price = javaslang.collection.List.ofAll(products).map(Product::getPrice).sum().doubleValue();

        order.setUser(user);
        order.setProducts(products);
        order.setCreatedAt(new Date());
        order.setPrice(price);
        order.setNote(note);

        shoppingOrdersDao.save(order);

        return order;
    }

    @Override
    public ShoppingOrder createOrder(String note, Long userId, List<Long> productIds) {
        return createOrder(note, userDao.findOne(userId),productIds);
    }

    @Override
    public List<ShoppingOrder> findAll() {
        return shoppingOrdersDao.findAll();
    }
}
