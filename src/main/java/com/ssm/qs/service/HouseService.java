package com.ssm.qs.service;

import com.qingsu.house.pojo.House;

import java.util.List;

public interface HouseService {

    List<House> findAllHouse();

    List<House> selectHouse(House house);

    void addHouse(House house);
}
