package com.example.web;

import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("list")
    public String list(Product product, ModelMap map) {
        List<Product> products = productService.findAll();

        List<Map<String,String>> productMaps = products.stream().map(e->{
            Map<String,String> productMap = new HashMap<>();
            // products to map
            productMap.put("id", e.getId().toString());
            productMap.put("name", e.getName());
            productMap.put("price", e.getPrice().toString());
            productMap.put("quantity", e.getQuantity().toString());
            return productMap;
        }).collect(Collectors.toList());

        map.addAttribute("products", productMaps);
        return "/products/list";
    }

    @GetMapping("form")
    public String form(ModelMap map) {
        return "/products/form";
    }

    @PostMapping("add")
    public String add(Product product) throws ParseException {
        productService.save(product);
        return "redirect:/products/list ";
    }

    @GetMapping("editForm/{id}")
    public String editForm(@PathVariable Long id, ModelMap map) {
        Product product = productService.findOne(id);
        Map productMap = new HashMap<>();
        productMap.put("id", product.getId().toString());
        productMap.put("name", product.getName());
        productMap.put("price", product.getPrice());
        productMap.put("quantity", product.getQuantity());

        map.addAttribute("product", productMap);
        return "/products/editForm";
    }

    @PostMapping("edit")
    public String edit(Product product) {
        productService.save(product);
        return "redirect:/products/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products/list";
    }


    /*
    @GetMapping("search")
    public String search(Product product, ModelMap map) {
        List<Product> products = productService.findAll(product);
        map.addAttribute("products", products);
        return "/product/list";
    }

    */
}
