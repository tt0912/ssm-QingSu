package com.ssm.qs.service.impl;

import com.qingsu.house.dao.HouseDao;
import com.qingsu.house.pojo.House;
import com.qingsu.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{

    @Autowired
    private HouseDao houseDao;

    @Override
    public List<House> findAllHouse() {
        return houseDao.findAllHouse();
    }

    @Override
    public List<House> selectHouse(House house) {
        return houseDao.selectHouse(house);
    }

    @Override
    public void addHouse(House house) {
        houseDao.addHouse(house);
    }
}
