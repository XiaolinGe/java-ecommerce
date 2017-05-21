package com.example.web.front;

import com.example.entity.ProductVO;
import com.example.entity.User;
import com.example.service.ShoppingOrderService;
import static javaslang.API.*;

import javaslang.API;
import javaslang.collection.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gezilin on 6/05/17.
 */

@Controller("FrontOrderController")
@RequestMapping("/front/orders")
public class OrderController {
    public static final String LOGIN_USER = "loginUser";
    public static final String CART = "CART";

    @Autowired
    private ShoppingOrderService shoppingOrderService;

    @PostMapping("add")
    public String add(HttpSession session) throws ParseException {
        shoppingOrderService.createOrder("From Front Orders", (User) session.getAttribute(LOGIN_USER), (Map<Long, Long>) session.getAttribute(CART));
        return "redirect:/front/products/list ";
    }

    /**
     * 开始一个新的需求
     * 1 先写controller, 因为它是前后端分界点， 确定＊用户输入＊的参数，意思就是，从httprequsest获取的参数，
     * 2 写service 的接口，确定业务的输入参数，包括用户输入，还有状态参数，比如session中的用户信息..
     * 3 写service空实现，让编译通过
     * 4 写test case,至少写一个positive test case
     * 5 实现service 通过test case
     * 6 编写前端页面
     * @param id
     * @return
     */
    @GetMapping("addtocart/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        if(session.getAttribute(CART) == null) {
            session.setAttribute(CART,Map());
        }


        Map<Long,Long> cart  = (Map<Long, Long>) session.getAttribute(CART);
        Map<Long,Long> newCart = shoppingOrderService.addToCart(cart,id,1L);
        session.setAttribute(CART, newCart);

        return "redirect:/front/products/list";
    }

    @GetMapping("cart")
    public String cart(HttpSession session, ModelMap map) {
        Map<Long, Long> cart = (Map<Long, Long>) session.getAttribute(CART);

        javaslang.collection.List<ProductVO> productVOs =  shoppingOrderService.getCart(cart);
        map.addAttribute("productVOs", productVOs.toJavaList());
        return "/front/products/shoppingCart";
    }
    /**
     * String s = new String("x");  //构造函数创建对象，本质
     * String s = "s";  //字面量 　liters
     * String s = API.STRING("s"); //方法调用，自定义。
     *
     *
     * List list = new List();
     * list.add(1);
     * list.add(2);
     *
     * String s = "x";
     * List list = [1,2,3,4];
     * List list = A.x(1,2,3,4)
     * List list = A.B(1,2,3)
     *
     * var map  =  {a:1,b:2,c:3};
     *
     * A.x(a,1);
     *
     *
     */

}
