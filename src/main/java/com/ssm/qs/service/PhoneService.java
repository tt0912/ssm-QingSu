package com.ssm.qs.service;

import com.ssm.qs.pojo.Phone;

public interface PhoneService {

    //1.插入
    void addPhone(Phone phone);

    //2.删除
    void deletePhone(String phone);

    //3.验证
    long checkCode(Phone phone);
}
