package com.example;

import com.example.dao.FooDao;
import com.example.dao.UserDao;
import com.example.entity.Foo;
import com.example.entity.User;
import com.example.enums.Status;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

@DataJpaTest
@Transactional
/**
 * Dao layer testing  每次执行不会修改数据库数据,使用的是内嵌数据库
 */
public class JpaTests {

    @Autowired
    private FooDao fooDao;

    @Autowired
    private UserDao userDao;

    @Autowired

    @Test
    public void  testFindByStatus(){
        fooDao.deleteAll();
        Foo foo  = new Foo();
        foo.setStatus(Status.FINISH);
        fooDao.save(foo);
        Foo foo2  = new Foo();
        foo2.setStatus(Status.INPROGRESS);
        fooDao.save(foo2);
        assertThat(fooDao.findByStatus(Status.FINISH).size()).isEqualTo(1);

    }

    @Test
    public void  addAndEditUser() {
        userDao.deleteAll();
        User user = new User();
        user.setName("user1");
        user.setAge(18);
        userDao.save(user);
        User user2 =  userDao.findByName("user1").get();
        user2.setName("user2");
        userDao.save(user2);
        assertThat(userDao.getOne(user.getId()).getName()).isEqualTo("user2");
    }

    @Ignore
    @Test
    public void createProduct(){

    }

    @Ignore
    @Test
    public void createOrders(){

    }

}
