package com.ssm.qs.controller;

import com.ssm.qs.pojo.Order;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private UserService userService;

    @RequestMapping("create_order.html")
    @ResponseBody
    public Map<String, Object> create(String ticket, Order order) {

        //1.根据票据拿id
        int userId = userService.getUID(ticket);
        order.setUserId(userId);
    }
}
