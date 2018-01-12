package com.ssm.qs.controller;

import com.qingsu.house.pojo.House;
import com.qingsu.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("perRelease")
    public String preRelease(){
        return "/releaseHouse.jsp";
    }

    @RequestMapping("findAllHouse")
    @ResponseBody
    public Map<String,Object> findAllHouse(){
        List<House> houses=houseService.findAllHouse();
        Map<String,Object> result=new HashMap<>();
        result.put("houses",houses);
        result.put("status",true);
        return result;
    }

    @RequestMapping("selectHouse")
    @ResponseBody
    public Map<String,Object> selectHouse(House house){
        List<House> houses=houseService.selectHouse(house);
        Map<String,Object> result=new HashMap<>();
        if(houses.size()>0){
            result.put("house",houses);
            result.put("status",true);
            result.put("message","添加成功");
        }
        result.put("statue",false);
        result.put("message","抱歉，暂未查找到相应结果");
        return result;
    }

    @RequestMapping("addHouse")
    @ResponseBody
    public Map<String,Object> addHouse(House house){
        Map<String,Object> result=new HashMap<>();
        if(house.getId()==null){
            house.setUserId(2);//暂时写死，等用户登录后从session中取得userId注入(HttpSession session.getUserid())
            houseService.addHouse(house);
            result.put("status",true);
            result.put("message","添加成功");
        }
        result.put("status",false);
        result.put("message","添加失败");
        return result;
    }



}
