package com.example.web.front;

import com.example.entity.User;
import com.example.service.UserService;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * Created by gezilin on 6/05/17.
 */

@Controller("FrontUserController")
@RequestMapping("/front/users")
public class UserController {
    public static final String LOGIN_USER = "loginUser";

    @Autowired
    private UserService userService;

    @GetMapping("loginForm")
    public String loginForm() {
        return "/front/loginForm";
    }

    @GetMapping("login")
    public String login(String loginName, String password, HttpSession session) {

        Option<User> userOpt = userService.login(loginName, password);

        if (userOpt.isEmpty()) {
            return "redirect:/front/loginForm";
        } else {
            session.setAttribute(LOGIN_USER, userOpt.get());
            return "redirect:/front/products/list";
        }
        /**
         * Connection conn = getConnectionFromDB("mysql://localhost:3306/shop","root","root")
         * conn.exec("insert into xxx");
         * conn.exec("insert into yyy");
         *
         * conn.setAutocommit(false)
         * conn.exec("insert into xxx");
         * conn.exec("insert into yyy");
         * conn.submit();
         * conn.exec("insert into zzz");
         */

    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(LOGIN_USER);
        session.removeAttribute("CART");

        return "redirect:/front/users/loginForm";
    }

    @GetMapping("")
    public String index(HttpSession session, ModelMap modelMap) {
        Object obj = session.getAttribute(LOGIN_USER);
        modelMap.addAttribute(LOGIN_USER, session.getAttribute(LOGIN_USER));
        return "/front/products/list";
    }

    @GetMapping("403")
    public String noAccess() {
        return "/admin/403";
    }
}
