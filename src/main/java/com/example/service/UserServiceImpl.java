package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String name){
        if(name.isEmpty()){
            System.out.println(1);
        }else {
            System.out.println(2);
        }
        return  userDao.findByName(name);
    }
    @Override
    public List<User> findAll(){
        return userDao.findAll();
    }
}
