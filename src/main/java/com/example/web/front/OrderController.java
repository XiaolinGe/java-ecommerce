package com.example.web.front;

import com.example.entity.User;
import com.example.service.ShoppingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Created by gezilin on 6/05/17.
 */

@Controller("FrontOrderController")
@RequestMapping("/front/orders")
public class OrderController {
    public static final String LOGIN_USER = "loginUser";

    @Autowired
    ShoppingOrderService shoppingOrderService;

    @PostMapping("add")
    public String add(Long[] productIds, HttpSession session) throws ParseException {



        shoppingOrderService.createOrder("From Front Orders", (User) session.getAttribute(LOGIN_USER), Arrays.asList(productIds));
        return "redirect:/front/products/list ";
    }
}
