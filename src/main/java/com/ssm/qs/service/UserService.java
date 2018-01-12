package com.ssm.qs.service;

import com.ssm.qs.pojo.Info;
import com.ssm.qs.pojo.User;

public interface UserService {

    //1.手机登录查用户
    User getUser(User user);

    //2.新建用户
    int addUser(User user);

    //3.插入登录票据
    int addTicket(Info info);

    //5.更新密码
    void updateUser(User user);

    //6.退出登录
    void deleteTicket(String ticket);

}
