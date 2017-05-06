package com.example.web.admin;

import com.example.dao.ProductDao;
import com.example.dao.UserDao;
import com.example.entity.Product;
import com.example.entity.ShoppingOrder;
import com.example.entity.User;
import com.example.service.ShoppingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ShoppingOrderService shoppingOrderService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;


    @GetMapping("list")
    public String list(ShoppingOrder order, ModelMap map) {

        List<ShoppingOrder> orders = shoppingOrderService.findAll();

        map.addAttribute("orders", orders);
        return "/orders/list";
    }

    @GetMapping("form")
    public String form(ModelMap map) {

        List<User> users = userDao.findAll();
        List<Product> products = productDao.findAll();
        map.addAttribute("users", users);
        map.addAttribute("products", products);
        return "/orders/form";
    }

    @PostMapping("add")
    public String add(String note, Long userId, Long[] productIds) throws ParseException {
        shoppingOrderService.createOrder(note, userId, Arrays.asList(productIds));
        return "redirect:/orders/list ";
    }

}
