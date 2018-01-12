package com.ssm.qs.service;

import com.ssm.qs.pojo.House;

import java.util.List;

public interface HouseService {

    List<House> findAllHouse();

    List<House> selectHouse(House house);

    void addHouse(House house);
}
