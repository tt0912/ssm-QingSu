package com.ssm.qs.service.impl;

import com.ssm.qs.dao.CouponDao;
import com.ssm.qs.pojo.Coupon;
import com.ssm.qs.pojo.Info;
import com.ssm.qs.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Override
    public List<Coupon> getCouponList() {
        return couponDao.getCouponList();
    }

    @Override
    public void gainCoupon(Info info) {
        couponDao.gainCoupon(info);
    }

    @Override
    public List<Coupon> showCoupon(String ticket) {
        return couponDao.showCoupon(ticket);
    }

    @Override
    public List<Coupon> useCoupon(Info info) {
        return couponDao.useCoupon(info);
    }
}
