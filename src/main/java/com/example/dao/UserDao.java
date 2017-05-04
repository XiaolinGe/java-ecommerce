package com.example.dao;

import com.example.entity.User;
import javaslang.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    Option<User> findByName(String name);


    Option<User> findByEmail(String email);


    Option<User> findByNameOrEmail(String name, String email);
}
