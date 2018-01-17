package com.ssm.qs.pojo;

import java.util.Date;

/**
 * Author 田宇
 * Date   2018/1/13 0013 15:21
 * Description 优惠券
 */

public class Coupon {

    private Integer id;
    private String type;
    private String name;
    private Date startTime;
    private Date endTime;
    private double discountAmount;
    private double condition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", discountAmount=" + discountAmount +
                ", condition=" + condition +
                '}';
    }
}
