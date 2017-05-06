package com.example.web.front;

import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by gezilin on 6/05/17.
 */

@Controller("FrontProductController")
@RequestMapping("/front/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public String list(Product product, ModelMap map) {
        List<Product> products = productService.findAll();
        map.addAttribute("products", products);
        return "front/products/list";
    }

}
