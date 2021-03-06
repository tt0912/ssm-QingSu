package com.ssm.qs.dao;

import com.ssm.qs.pojo.Phone;

/**
 *  Author 田宇
 *  Date   2018/1/20 0020 14:40
 *  Description 插入、删除
 */
public interface PhoneDao {

    //1.插入
    void addPhone(Phone phone);

    //2.删除
    void deletePhone(String phone);

    //3.验证
    long checkCode(Phone phone);
}
