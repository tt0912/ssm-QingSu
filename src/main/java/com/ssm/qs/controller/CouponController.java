package com.ssm.qs.controller;

import com.ssm.qs.pojo.Coupon;
import com.ssm.qs.pojo.Info;
import com.ssm.qs.service.CouponService;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private UserService userService;

    //结果容器
    Map<String, Object> result = new HashMap<>();

    //1.分页展示所有优惠券
    @RequestMapping("/list_coupon.html")
    @ResponseBody
    public Map<String, Object> list() {

        List<Coupon> couponList = couponService.getCouponList();
        result.put("success", true);
        result.put("result", couponList);
        result.put("error", null);
        return result;
    }

    //2.领取优惠券
    @RequestMapping("/gain_coupon.html")
    @ResponseBody
    public Map<String, Object> gain(Info info) {

        //1.根据ticket查id
        int uid = userService.getUID(info.getTicket());
        info.setUid(uid);
        //2.插入
        couponService.gainCoupon(info);
        result.put("success", true);
        result.put("result", true);
        result.put("error", null);
        return result;
    }

    //3.展示“我的没过期的优惠券”
    @RequestMapping("/show_coupon.html")
    @ResponseBody
    public Map<String, Object> show(String ticket) {

        List<Coupon> couponList = couponService.showCoupon(ticket);
        result.put("success", true);
        result.put("result", couponList);
        result.put("error", null);
        return result;
    }

    //4.订单可以使用的优惠券
    @RequestMapping("/use_coupon.html")
    @ResponseBody
    public Map<String, Object> use(Info info) {

        List<Coupon> couponList = couponService.useCoupon(info);
        result.put("success", true);
        result.put("result", couponList);
        result.put("error", null);
        return result;
    }
}
