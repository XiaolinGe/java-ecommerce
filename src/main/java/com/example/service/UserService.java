package com.example.service;

import com.example.entity.User;
import javaslang.control.Option;

import java.util.List;

public interface UserService {

    boolean register(User user);

    Option<User> login(String loginName, String password);

    boolean createOrder(List<Long> productIds);

    List<User> findAll();

    void save(User user);

    User findOne(Long id);

    void delete(Long id);

    Option<User> findByName(String name);
}

