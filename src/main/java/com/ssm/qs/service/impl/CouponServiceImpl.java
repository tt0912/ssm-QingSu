package com.ssm.qs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<Coupon> getCouponList(int page, int rows) {

        PageHelper.startPage(page,rows);
        List<Coupon> couponList = couponDao.getCouponList();
        PageInfo pageInfo = new PageInfo(couponList);
        return pageInfo;
    }

    @Override
    public void gainCoupon(Info info) {
        couponDao.gainCoupon(info);
    }

    @Override
    public PageInfo<Coupon> showCoupon(String ticket,int page, int rows) {

        PageHelper.startPage(page,rows);
        List<Coupon> couponList = couponDao.showCoupon(ticket);
        PageInfo<Coupon> pageInfo = new PageInfo<>(couponList);
        return pageInfo;
    }

    @Override
    public List<Coupon> useCoupon(Info info) {
        return couponDao.useCoupon(info);
    }
}
