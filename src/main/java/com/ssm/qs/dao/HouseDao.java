package com.ssm.qs.dao;


import com.ssm.qs.pojo.House;

import java.util.List;

public interface HouseDao {

    List<House> findAllHouse();

    List<House> selectHouse(House house);

    void addHouse(House house);

    House findHouseById(Integer id);

    List<House> findHouseByLocation(Double lat, Double lon);
}
