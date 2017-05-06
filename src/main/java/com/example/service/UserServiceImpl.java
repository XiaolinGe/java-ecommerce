package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import javaslang.API;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public boolean register(User user) {
        if (userDao.findByEmail(user.getEmail()).isDefined()) {
            return false;
        }
        if (userDao.findByName(user.getName()).isDefined()) {
            return false;
        }
        userDao.save(user);
        return true;
    }

    @Override
    public Option<User> login(String loginName, String password) {
        Option<User> userOpt = userDao.findByName(loginName);
        Option<User> userOpt2 = userDao.findByEmail(loginName);


        javaslang.collection.List<Option<User>> list = API.List(userOpt, userOpt2)
                .filter(Option::isDefined)
                .filter(e -> password.equals(e.get().getPassword()));
        return  list.toOption().getOrElse(Option.none());

    }

    @Override
    public boolean createOrder(List<Long> productIds) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public Option<User> findByName(String name) {
        return userDao.findByName(name);
    }

}
