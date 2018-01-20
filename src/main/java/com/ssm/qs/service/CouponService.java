package com.ssm.qs.service;

import com.github.pagehelper.PageInfo;
import com.ssm.qs.pojo.Coupon;
import com.ssm.qs.pojo.Info;

import java.util.List;

public interface CouponService {

    //1.返回所有优惠券
    PageInfo<Coupon> getCouponList(int page, int rows);

    //2.获取优惠券，添加关系
    void gainCoupon(Info info);

    //3.展示拥有的优惠券
    PageInfo<Coupon> showCoupon(String ticket,int page, int rows);

    //4.创建订单时可以用的优惠券
    List<Coupon> useCoupon(Info info);
}
