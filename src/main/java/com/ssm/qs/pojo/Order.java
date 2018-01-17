package com.ssm.qs.pojo;

import java.util.Date;

public class Order {

    private Integer id;//订单id
    private Date createdDate;//下单时间
    private Integer status;//订单状态
    private String orderNum;//订单编号
    private String tradeNum;//支付编号
    private Date payTime;//支付时间
    private int day;//入住天数
    private double discount;//折扣
    private double orginal;//原始金额
    private double actual;//实际金额
    private Integer userId;//用户id
    private Integer couponId;//优惠券id
    private Integer houseId;//房源id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getOrginal() {
        return orginal;
    }

    public void setOrginal(double orginal) {
        this.orginal = orginal;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", orderNum='" + orderNum + '\'' +
                ", tradeNum='" + tradeNum + '\'' +
                ", payTime=" + payTime +
                ", day=" + day +
                ", discount=" + discount +
                ", orginal=" + orginal +
                ", actual=" + actual +
                ", userId=" + userId +
                ", couponId=" + couponId +
                ", houseId=" + houseId +
                '}';
    }
}
