package com.ssm.qs.controller;

import com.ssm.qs.pojo.Order;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private UserService userService;
    //结果容器
    Map<String,Object> result = new HashMap<>();

    //1.创建订单
    @RequestMapping("/order_create.html")
    @ResponseBody
    public Map<String, Object> create(String ticket, Order order) {

        //1.根据票据拿userId
        int userId = userService.getUID(ticket);
        order.setUserId(userId);
        //2.订单号
        order.setOrderNum(UUID.randomUUID().toString().replaceAll("-",""));

        return result;
    }
}
