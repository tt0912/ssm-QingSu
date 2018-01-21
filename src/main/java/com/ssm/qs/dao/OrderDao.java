package com.ssm.qs.dao;

import com.ssm.qs.pojo.OrderList;

import java.util.List;

public interface OrderDao {

    void addOrder(OrderList orderList);

    List<OrderList> fingAllOrder(Integer userId);

    void uplodaOrder(int id);

    void deleteOrder(int id);
}
