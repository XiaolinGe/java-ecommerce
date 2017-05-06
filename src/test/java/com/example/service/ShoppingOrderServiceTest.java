package com.example.service;

import com.example.dao.ShoppingOrdersDao;
import com.example.dao.ProductDao;
import com.example.dao.UserDao;
import com.example.entity.ShoppingOrder;
import com.example.entity.Product;
import com.example.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

@DataJpaTest
@Transactional

public class ShoppingOrderServiceTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShoppingOrdersDao shoppingOrdersDao;

    @Autowired
    private ShoppingOrderService shoppingOrderService;

    @Before
    public void setUp() throws Exception {
        shoppingOrdersDao.deleteAllInBatch();
        userDao.deleteAllInBatch();
        productDao.deleteAllInBatch();
    }


    // ShoppingOrder createOrder(User user, List<Long> productIds);

    @Test
    public void createOrderSuccess() throws Exception {
        // Given
        createUser();
        createProducts();
        // When
        User user = userDao.findByName("customer").get();
        User user2 = userDao.findByName("customerNoOrder").get();
        //List<Long> productIds = productDao.findAll().stream().map(e->e.getId()).collect(Collectors.toList());
         List<Long> productIds = javaslang.collection.List.ofAll(productDao.findAll()).map(Product::getId).toJavaList();
         ShoppingOrder order = shoppingOrderService.createOrder("note",user, productIds);


        Double price = javaslang.collection.List.ofAll(productDao.findAll()).map(Product::getPrice).sum().doubleValue();


         //Then
        ShoppingOrder newOrder = shoppingOrdersDao.findOne(order.getId());
        System.out.println("Order in Db " + newOrder);
        User newUser = userDao.findByName("customer").get();
        System.out.println("User in Db " + newUser);

        List<Product> newProducts = productDao.findAll();
        System.out.println("prodects in db: "+ newProducts);

        assertEquals("customer", newOrder.getUser().getName());
        assertEquals(2,newOrder.getProducts().size());
        assertEquals(1,newUser.getShoppingOrders().size());
        assertEquals(0,user2.getShoppingOrders().size());
        assertEquals(price+"",order.getPrice()+"");
        assertEquals(1, shoppingOrdersDao.findAll().size());

    }

    public void createUser() throws Exception {
        User user = new User().setName("customer");
        userDao.save(user);

        User user2 = new User().setName("customerNoOrder");
        userDao.save(user2);
    }

    private void createProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product().setName("product1").setPrice(100.00).setQuantity(10L);
        Product product2 = new Product().setName("product2").setPrice(200.00).setQuantity(20L);

        products.add(product1);
        products.add(product2);

        productDao.save(products);
    }

}