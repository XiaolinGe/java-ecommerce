package com.example.web;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(Model model) {
        List<User> models = service.findAll();
        model.addAttribute("todos", models);
        return "user/list";
    }
}
