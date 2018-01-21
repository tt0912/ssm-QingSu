package com.ssm.qs.controller;

import com.ssm.qs.pojo.OrderList;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.OrderService;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    //结果容器
    Map<String,Object> result = new HashMap<>();

    //1.创建订单
    @RequestMapping("/order_create.html")
    @ResponseBody
    public Map<String, Object> create(String ticket, OrderList orderList, HttpSession session) {

        //1.从session中取用户id
        User user = (User)session.getAttribute("SESSION_USER");
        orderList.setUserId(user.getId());
        //2.订单号
        orderList.setOrderNum(UUID.randomUUID().toString().replaceAll("-",""));
        orderService.addOrder(orderList);
        result.put("success", true);
        result.put("error", null);
        result.put("message","成功");
        return result;
    }

    //查找用户的所有订单信息
    @RequestMapping("/order_findAllOrder.html")
    public Map<String,Object> findAllOrder(Model model, HttpSession session){
        User user=(User)session.getAttribute("SESSION_USER");
        Integer userId=user.getId();
        List<OrderList> orderLists =orderService.fingAllOrder(userId);
        if(orderLists.size()>0) {
            result.put("orders", orderLists);
            result.put("success", true);
            result.put("error", null);
            result.put("message", "成功");
            return result;
        }else {
            result.put("success", false);
            result.put("message","抱歉，暂未查找到相应结果");
            return result;
        }
    }


    //下订单，更改订单状态为支付
    @RequestMapping("/order_update.html")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Map<String,Integer> param){
        Integer id = param.get("id");
        orderService.uplodaOrder(id);
        result.put("success",true);
        result.put("error",null);
        result.put("message","更改状态成功");
        return result;
    }

    //4.删除订单
    @RequestMapping("/order_delete.html")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody Map<String,Integer> param)throws Exception{
        Integer id = param.get("id");
        if(id!=null){
            orderService.deleteOrder(id);
            result.put("success",true);
            result.put("error",null);
            result.put("message","更改状态成功");
        }else {
            result.put("success",false);
            result.put("message","更改状态失败");
        }
        return result;
    }
}
