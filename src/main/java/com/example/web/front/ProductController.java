package com.example.web.front;

import com.example.entity.Product;
import com.example.entity.User;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gezilin on 6/05/17.
 */

@Controller("FrontProductController")
@RequestMapping("/front/products")
public class ProductController {
    public static final String LOGIN_USER = "loginUser";

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public String list(HttpSession session, ModelMap map) {

        map.addAttribute("products", productService.findAll());
        map.addAttribute("user", session.getAttribute(LOGIN_USER));
        return "front/products/list";
    }

}
