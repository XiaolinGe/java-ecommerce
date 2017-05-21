package com.example.service;

import com.example.dao.ProductDao;
import com.example.dao.ShoppingOrdersDao;
import com.example.dao.UserDao;
import com.example.entity.Product;
import com.example.entity.ProductVO;
import com.example.entity.ShoppingOrder;
import com.example.entity.User;
import javaslang.API;
import javaslang.collection.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        Product product1 = new Product().setPrice(10.00).setName("product1").setQuantity(10L);
        Product product2 = new Product().setPrice(20.00).setName("product2").setQuantity(20L);

        productDao.save(product1);
        productDao.save(product2);
        // When
        User user = userDao.findByName("customer").get();
        User user2 = userDao.findByName("customerNoOrder").get();

        Map<Long, Long> cart1 = API.Map(product1.getId(),1L,product2.getId(),2L);

        ShoppingOrder order = shoppingOrderService.createOrder("note", user, cart1);

        Double price = cart1.map(e-> productDao.findOne(e._1).getPrice()).sum().doubleValue();



        //Then
        ShoppingOrder newOrder = shoppingOrdersDao.findOne(order.getId());
        System.out.println("Order in Db " + newOrder);
        User newUser = userDao.findByName("customer").get();
        System.out.println("User in Db " + newUser);

        List<Product> newProducts = productDao.findAll();
        System.out.println("prodects in db: " + newProducts);

        assertEquals("customer", newOrder.getUser().getName());
        assertEquals(2, newOrder.getProducts().size());
        //assertEquals(1, newUser.getShoppingOrders().size());
        //assertEquals(0, user2.getShoppingOrders().size());
        assertEquals(price + "", order.getPrice() + "");
        assertEquals(1, shoppingOrdersDao.findAll().size());
        assertEquals(9L, product1.getQuantity().longValue());
        assertEquals(18L, product2.getQuantity().longValue());

    }

    @Test
    public void addToCart() throws Exception {
        // // Given

        // When
        javaslang.collection.Map<Long, Long> cart0 = API.Map();
        javaslang.collection.Map<Long, Long> cart1 = API.Map(2L, 3L);

        javaslang.collection.Map<Long, Long> newCart0 = shoppingOrderService.addToCart(cart0, 1L, 1L);
        javaslang.collection.Map<Long, Long> newCart1 = shoppingOrderService.addToCart(cart1, 2L, 1L);

        // Then
        assertEquals(API.Map(1L, 1L), newCart0); /* 关于Map的测试用例，要考虑Map的key为空的情况*/
        assertEquals(API.Map(2L, 4L), newCart1);
    }

    @Test
    public void getCart() throws Exception {
        //Give
        Product product1 = new Product().setName("product1").setPrice(100.00).setQuantity(10L);
        Product product2 = new Product().setName("product2").setPrice(100.00).setQuantity(10L);
        productDao.save(product1);
        productDao.save(product2);

        //When
        javaslang.collection.Map<Long, Long> cart1 = API.Map(product1.getId(), 1L, product2.getId(), 2L);

        javaslang.collection.List<ProductVO> newCart1 = shoppingOrderService.getCart(cart1);
        // Then

        assertEquals(2,newCart1.size());
        assertEquals(product1.getId(), newCart1.get(0).getId());
        assertEquals(product2.getId(), newCart1.get(1).getId());
        assertEquals(product1.getName(), newCart1.get(0).getName());
        assertEquals(product2.getName(),newCart1.get(1).getName());
        assertEquals(cart1.get(product1.getId()).get(), newCart1.get(0).getQuantityInCart());
        assertEquals(cart1.get(product2.getId()).get(), newCart1.get(1).getQuantityInCart());


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