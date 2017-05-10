package com.example.service;


import com.example.dao.ProductDao;
import com.example.dao.ShoppingOrdersDao;
import com.example.dao.UserDao;
import com.example.entity.Product;
import com.example.entity.ProductVO;
import com.example.entity.ShoppingOrder;
import com.example.entity.User;
import com.example.web.exception.BussinessException;
import javaslang.collection.Map;
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
        // TODO filter data form DB
        List<Product> products = javaslang.collection.List.ofAll(productDao.findAll()).filter(e -> productIds.contains(e.getId())).toJavaList();
        Double price = javaslang.collection.List.ofAll(products).map(Product::getPrice).sum().doubleValue();


        javaslang.collection.List.ofAll(products).map(e -> e.setQuantity(e.getQuantity() - 1));
        //     throw new BussinessException("sold out");
        if (javaslang.collection.List.ofAll(products).exists(e -> e.getQuantity() < 0)) {
            throw new BussinessException("this product quantity is less then 0");
        }


        productDao.save(products);
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
        return createOrder(note, userDao.findOne(userId), productIds);
    }

    @Override
    public List<ShoppingOrder> findAll() {
        return shoppingOrdersDao.findAll();
    }

    @Override
    public Map<Long, Long> addToCart(Map<Long, Long> shoppingCart, Long productId, Long productQuantity) {
        Long currQuantity = shoppingCart.get(productId).getOrElse(0L);

        return shoppingCart.put(productId, currQuantity + productQuantity);

    }

    @Override
    public javaslang.collection.List<ProductVO> getCart(Map<Long, Long> cart) {

        /*
        * Map<Long, Long>   Tuple2<> product.id, quantityInCart = > List<ProductVO> product.name, product.id, quantityInCart;
        *
        * List<String>  String = > List<Integer> Integer
        *
        * List<>
        * */


        return cart.map(e -> new ProductVO().setId(e._1).setName(productDao.findOne(e._1).getName()).setQuantityInCart(e._2))
                .toList();
    }
}
