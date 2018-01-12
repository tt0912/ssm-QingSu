package com.ssm.qs.service.impl;

import com.ssm.qs.dao.UserDao;
import com.ssm.qs.pojo.Info;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int addTicket(Info info) {
        return userDao.addTicket(info);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteTicket(String ticket) {
        userDao.deleteTicket(ticket);
    }
}
