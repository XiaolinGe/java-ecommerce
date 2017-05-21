package com.example.web;



import com.example.entity.User;
import com.example.web.exception.NoPermission;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.stream.Stream;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    public static final String LOGIN_USER = "loginUser";


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        HttpSession session = request.getSession();

        if(session.getAttribute(LOGIN_USER) == null) {
            response.sendRedirect("/front/users/loginForm");
            return false;
        }


        String url = request.getRequestURI();
        User user = (User) session.getAttribute(LOGIN_USER);

        if (url.startsWith("/admin")) {
            if(!user.getRole().equals("admin")) {
                throw  new NoPermission("no permission");
              //  response.sendRedirect("/front/users/403");
            }
        }



        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }
    /**
     *
     * function f1(f2){
     *     sout(before);
     *     f2();
     *     sout(after);
     * }
     *
     * function add(a b){
     *     return a+b;
     *     sout(1)
     * }
     *
     * fucntion min(a b){
     *     return a<b?a:b;
     * }
     *
     * f1(add);
     * f1(min)
     *
     */
}
