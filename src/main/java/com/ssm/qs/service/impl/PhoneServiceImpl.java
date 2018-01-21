package com.ssm.qs.service.impl;

import com.ssm.qs.dao.PhoneDao;
import com.ssm.qs.pojo.Phone;
import com.ssm.qs.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    private PhoneDao phoneDao;

    @Override
    public void addPhone(Phone phone) {
        phoneDao.addPhone(phone);
    }

    @Override
    public void deletePhone(String phone) {
        phoneDao.deletePhone(phone);
    }

    @Override
    public long checkCode(Phone phone) {
        return phoneDao.checkCode(phone);
    }
}
