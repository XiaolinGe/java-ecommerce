package com.example.web.admin;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("list")
    public String list(User user, ModelMap map) {
        List<User> users = userService.findAll();

        List<Map<String,String>> userMaps = users.stream().map(e->{
            Map<String,String> userMap = new HashMap<>();
            // user to map
            userMap.put("id", e.getId().toString());
            userMap.put("name", e.getName());
            userMap.put("age", e.getAge().toString());
            userMap.put("password", e.getPassword());
            userMap.put("gender", e.getGender());
            userMap.put("phone", e.getPhone());
            userMap.put("email", e.getEmail());
            return userMap;
        }).collect(Collectors.toList());

        map.addAttribute("users", userMaps);
        return "/users/list";
    }

    @GetMapping("form")
    public String form(ModelMap map) {
        return "/users/form";
    }

   @PostMapping("add")
    public String add(User user) throws ParseException {
        userService.save(user);
        return "redirect:/users/list ";
    }

     @GetMapping("editForm/{id}")
    public String editForm(@PathVariable Long id, ModelMap map) {
        User user = userService.findOne(id);
        Map userMap = new HashMap<>();
        userMap.put("id", user.getId().toString());
        userMap.put("name", user.getName());
        userMap.put("age", user.getAge().toString());
        userMap.put("password", user.getPassword().toString());
        userMap.put("gender", user.getGender());
        userMap.put("phone", user.getPhone());
        userMap.put("email", user.getEmail());

        map.addAttribute("user", userMap);
        return "/users/editForm";
    }

    @PostMapping("edit")
    public String edit(User user) {
        userService.save(user);
        return "redirect:/users/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users/list";
    }


    /*
    @GetMapping("search")
    public String search(User user, ModelMap map) {
        List<User> users = userService.findAll(user);
        map.addAttribute("users", users);
        return "/user/list";
    }

    */
}
