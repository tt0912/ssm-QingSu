package com.ssm.qs.dao;

import com.ssm.qs.pojo.House;
import com.ssm.qs.pojo.Info;
import com.ssm.qs.pojo.User;

import java.util.List;

public interface UserDao {

    //1.手机登录查用户
    User getUser(User user);

    //2.新建用户
    int addUser(User user);

    //3.插入登录票据
    int addTicket(Info info);

    //4.查票据
    int getUID(String ticket);

    //5.更新密码
    void updateUser(User user);

    //6.退出登录
    void deleteTicket(String ticket);

    //7.添加收藏
    void addCollection(User user);

    //8.展示收藏
    List<House> findCollection(Integer id);

    //9.添加浏览记录
    void addBrowse(User user);

    //10.展示浏览记录
    List<House> findBrowse(Integer id);
}
