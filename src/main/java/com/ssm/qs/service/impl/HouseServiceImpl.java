package com.ssm.qs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.qs.dao.HouseDao;
import com.ssm.qs.pojo.House;
import com.ssm.qs.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Override
    public List<House> findAllHouse() {
        return houseDao.findAllHouse();
    }

    @Override
    public PageInfo<House> selectHouse(House house, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<House> houseList = houseDao.selectHouse(house);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public void addHouse(House house) {
        houseDao.addHouse(house);
    }

    @Override
    public House findHouseById(Integer id) {
        return houseDao.findHouseById(id);
    }
}
