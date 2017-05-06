package com.example.web.admin;

import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("list")
    public String list(ModelMap map) {
        List<Product> products = productService.findAll();
        map.addAttribute("products", products);
        return "/admin/products/list";
    }

    @GetMapping("form")
    public String form(ModelMap map) {
        return "/admin/products/form";
    }

    @PostMapping("add")
    public String add(Product product, MultipartFile file, HttpServletRequest request) throws IOException {
        String webRoot = request.getServletContext().getRealPath("/");
        String fileName = file.getOriginalFilename();
        File newFile = new File(webRoot,fileName);
        file.transferTo(newFile);
        product.setImage(fileName);

        productService.save(product);
        return "redirect:/admin/products/list ";
    }

    @GetMapping("editForm/{id}")
    public String editForm(@PathVariable Long id, ModelMap map) {
        Product product = productService.findOne(id);
        map.addAttribute("product", product);
        return "/admin/products/editForm";
    }

    @PostMapping("edit")
    public String edit(Product product,  MultipartFile file, HttpServletRequest request)  throws IOException{
        String webRoot = request.getServletContext().getRealPath("/");
        String fileName = file.getOriginalFilename();
        File newFile = new File(webRoot, fileName);
        file.transferTo(newFile);
        product.setImage(fileName);
        productService.save(product);
        return "redirect:/admin/products/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products/list";
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
