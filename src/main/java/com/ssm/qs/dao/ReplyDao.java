package com.ssm.qs.dao;

import com.ssm.qs.pojo.Reply;
import com.ssm.qs.pojo.User;

import java.util.List;

public interface ReplyDao {

    //1.发表评价
    void addReply(Reply reply);

    //2.查看用户评价
    List<User> getReply(int id);
}
