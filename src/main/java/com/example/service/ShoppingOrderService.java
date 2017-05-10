package com.example.service;

import com.example.entity.ShoppingOrder;
import com.example.entity.User;
import com.example.entity.ProductVO;
import javaslang.collection.Map;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ShoppingOrderService {

    @Transactional
    ShoppingOrder createOrder(String note, User user, List<Long> productIds);

    @Transactional
    ShoppingOrder createOrder(String note, Long userId, List<Long> productIds);

    List<ShoppingOrder> findAll();

    @Transactional
    Map<Long, Long> addToCart(Map<Long, Long> shoppingCart, Long productId, Long productQuantity);

    @Transactional
    javaslang.collection.List<ProductVO> getCart(Map<Long, Long> cart);



    /**
     *  product.id product.name quantity
     *  products quantity
     *  当需要返回一组数据的时候，用List, 而Map是用来返回一个对象的, Map和Object本质是一样的，都是一个数据
     *
     */

}
