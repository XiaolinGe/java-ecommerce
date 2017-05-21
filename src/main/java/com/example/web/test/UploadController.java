package com.example.web.test;

import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by gezilin on 6/05/17.
 */

@Controller
@RequestMapping("/test/products")
public class UploadController {
    @Autowired
    private ProductService productService;

    @GetMapping("/upload")
    public String upload() {

        return "/test/upload";
    }

    @PostMapping("/file")
    @ResponseBody
    public String file(MultipartFile file, HttpServletRequest request) throws IOException {

        String webRoot = request.getServletContext().getRealPath("/");
        System.out.println("WebRoot " + webRoot);
        String fileName = file.getOriginalFilename();
        File newfile = new File(webRoot,fileName);
        file.transferTo(newfile);

        return "<a href='http://localhost:8080/"+fileName+"'> image </a>";
    }


}
