package com.example.service;

import com.example.entity.ShoppingOrder;
import com.example.entity.User;

import java.util.List;

/**
 * Created by gezilin on 3/05/17.
 */
public interface ShoppingOrderService {



    ShoppingOrder createOrder(User user, List<Long> productIds);

}
