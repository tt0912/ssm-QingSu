package com.ssm.qs.service;

import com.ssm.qs.pojo.OrderList;

import java.util.List;

public interface OrderService {

    void addOrder(OrderList orderList);

    List<OrderList> fingAllOrder(Integer userId);
    void uplodaOrder(int id);

    void deleteOrder(int id);
}
