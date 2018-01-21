package com.ssm.qs.service.impl;

import com.ssm.qs.dao.OrderDao;
import com.ssm.qs.pojo.OrderList;
import com.ssm.qs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public void addOrder(OrderList orderList) {
        orderDao.addOrder(orderList);
    }

    @Override
    public List<OrderList> fingAllOrder(Integer userId) {
        return orderDao.fingAllOrder(userId);
    }

    @Autowired
    private OrderDao orderDao;



    public void uplodaOrder(int id) {
        orderDao.uplodaOrder(id);
    }

    public void deleteOrder(int id) {
        orderDao.deleteOrder(id);
    }
}
