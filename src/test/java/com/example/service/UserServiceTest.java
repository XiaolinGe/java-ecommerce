package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

@DataJpaTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;


    @Before
    public void setUp() throws Exception {
        userDao.deleteAllInBatch();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void registerSuccess() throws Exception {
        //Given

        //when
        User user = new User();
        user.setEmail("1@1.com");
        user.setPassword("xx");
        boolean result = userService.register(user);

        //then
        assertEquals(true,result);
        assertEquals(1,userDao.findAll().size());


    }

    @Test
    public void registerFailWithExistingEmail() throws Exception {
        //Given
        User user = new User();
        user.setEmail("1@1.com");
        user.setName("a");
        userDao.save(user);
        //when
        User user2 = new User();
        user2.setEmail("1@1.com");
        user2.setName("x");
        boolean result = userService.register(user2);

        //then
        assertEquals(false,result);
        assertEquals(1,userDao.findAll().size());


    }


    @Test
    public void registerFailWithExistingName() throws Exception {
        //Given
        User user = new User();
        user.setName("name");
        user.setEmail("1@1.com");
        userDao.save(user);
        //when
        User user2 = new User();
        user2.setName("name");
        user2.setEmail("2@1.com");
        boolean result = userService.register(user2);

        //then
        assertEquals(false,result);
        assertEquals(1,userDao.findAll().size());


    }



    @Test
    public void loginWithCorrectNameAndPassword() throws Exception {
        //Given
        createUser();
        //when
        String name = "admin";
        String password = "admin";
        boolean login = userService.login(name, password);
        //then
        assertEquals(true, login);
    }

    @Test
    public void loginWithCorrectNameAndInvalidPassword() throws Exception {
        //Given
        createUser();
        //when
        String name = "admin";
        String password = "wrongPassword";
        //then
        assertEquals(false, userService.login(name, password));
    }



    @Test
    public void loginWithCorrectEmailAndPassword() throws Exception {
        //Given
        createUser();
        //when
        String email = "test@gmail.com";
        String password = "admin";
        //then
        assertEquals(true, userService.login(email, password));
    }

    @Test
    public void loginWithCorrectEmailAndInvalidPassword() throws Exception {
        //Given
        createUser();
        //when
        String email = "test@gmail.com";
        String password = "wrongPassword";
        //then
        assertEquals(false, userService.login(email, password));
    }

    private void createUser() {
        User user = new User().setEmail("test@gmail.com").setPassword("admin").setName("admin");
        userDao.save(user);
    }


}