package com.example.dao;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>{

    User findByName(String name);
}
