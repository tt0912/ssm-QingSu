package com.ssm.qs.service.impl;

import com.ssm.qs.dao.UserDao;
import com.ssm.qs.pojo.House;
import com.ssm.qs.pojo.Info;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int getUID(String ticket) {
        return userDao.getUID(ticket);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteTicket(String ticket) {
        userDao.deleteTicket(ticket);
    }

    @Override
    public void addCollection(User user) {
        userDao.addCollection(user);
    }

    @Override
    public List<House> findCollection(Integer id) {
        return userDao.findCollection(id);
    }

    @Override
    public void addBrowse(User user) {
        userDao.addBrowse(user);
    }

    @Override
    public List<House> findBrowse(Integer id) {
        return userDao.findBrowse(id);
    }
}
