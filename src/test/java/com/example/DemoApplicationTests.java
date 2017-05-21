package com.example;

import com.example.dao.FooDao;
import com.example.dao.UserDao;
import com.example.entity.Foo;
import com.example.entity.User;
import com.example.enums.Status;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
/**
 * 每次执行会修改数据库数据
 */
public class DemoApplicationTests {

    @Autowired
    private FooDao fooDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void saveFoo() {
        Foo foo = new Foo();
        foo.setStatus(Status.FINISH);
        fooDao.save(foo);
    }



}
