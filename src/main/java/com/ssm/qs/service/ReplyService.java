package com.ssm.qs.service;

import com.github.pagehelper.PageInfo;
import com.ssm.qs.pojo.Reply;
import com.ssm.qs.pojo.User;

public interface ReplyService {

    //1.发表评价
    void addReply(Reply reply);

    //2.查看用户评价
    PageInfo<User> getReply(int id, int pageNum, int pageSize);
}
