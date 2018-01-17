package com.ssm.qs.dao;

import com.qingsu.house.pojo.House;

import java.util.List;

public interface HouseDao {

    List<House> findAllHouse();

    List<House> selectHouse(House house);

    void addHouse(House house);

    House findHouseById(Integer id);
}
