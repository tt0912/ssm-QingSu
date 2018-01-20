package com.ssm.qs.service;

import com.github.pagehelper.PageInfo;
import com.ssm.qs.pojo.House;

import java.util.List;

public interface HouseService {

    List<House> findAllHouse();

    PageInfo<House> selectHouse(House house, Integer pageNum);

    void addHouse(House house);

    House findHouseById(Integer id);

    List<House> findHouseByLocation(Double lat, Double lon);

}
