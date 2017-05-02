package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    User login(String name);

    List<User> findAll();
}
