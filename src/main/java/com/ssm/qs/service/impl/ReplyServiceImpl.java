package com.ssm.qs.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.qs.dao.ReplyDao;
import com.ssm.qs.pojo.Reply;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Override
    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }

    @Override
    public PageInfo<User> getReply(int id, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> replyList = replyDao.getReply(id);
        PageInfo<User> pageInfo = new PageInfo<User>(replyList);
        return pageInfo;
    }
}
