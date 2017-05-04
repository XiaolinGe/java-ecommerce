package com.example.dao;

import com.example.entity.Product;
import com.example.entity.ShoppingOrder;
import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

@DataJpaTest

public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ShoppingOrdersDao shoppingOrdersDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByName() throws Exception {
        User user = new User().setEmail("test@gmail.com").setPassword("admin").setName("admin");
        userDao.save(user);
        assertTrue(userDao.findByName("admin")!=null);
    }

    @Test
    public void findByEmail() throws Exception {
        User user = new User().setEmail("test@gmail.com").setPassword("admin").setName("admin");
        userDao.save(user);
        assertTrue(userDao.findByEmail("test@gmail.com")!=null);
    }

    @Test
    public void findByNameOrEmail() throws Exception {
        User user = new User().setEmail("test@gmail.com").setPassword("admin").setName("admin");
        userDao.save(user);
        assertEquals(true,userDao.findByNameOrEmail("test@gamil.com","test@gamil.com").isDefined());
    }

    @Test
    public void testAssociatedShoppingOrders(){
        Product product = new Product();
        product.setName("test");
        //productDao.save(product);
        testEntityManager.persist(product);

        List<Product> products  = new ArrayList<>();
        products.add(product);

        List<ShoppingOrder> orderList = new ArrayList<>();

        ShoppingOrder shoppingOrder = new ShoppingOrder();
        shoppingOrder.setNote("xx");
        shoppingOrder.setPrice(1.1);
        shoppingOrder.setProducts(products);
        testEntityManager.persist(product);
        //shoppingOrdersDao.save(shoppingOrder);

        orderList.add(shoppingOrder);
        User user = new User();
        user.setName("admin");
        user.setShoppingOrders(orderList);
        userDao.save(user);
        user = userDao.findOne(user.getId());
        System.out.println(user);
        assertEquals(true,!user.getShoppingOrders().isEmpty());
        ShoppingOrder newOrder = shoppingOrdersDao.findAll().get(0);
        System.out.println(newOrder);
        System.out.println(newOrder.getUser());
    }

}