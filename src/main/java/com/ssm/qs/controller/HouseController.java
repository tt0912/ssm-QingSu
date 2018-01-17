package com.ssm.qs.controller;


import com.github.pagehelper.PageInfo;
import com.ssm.qs.pojo.House;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.HouseService;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("house")
public class HouseController {

    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;

    @RequestMapping("perRelease")
    public String preRelease(){
        return "/releaseHouse.jsp";
    }

    //查询所有房源信息，已经过测试
    @RequestMapping("findAllHouse")
    @ResponseBody
    public Map<String,Object> findAllHouse(){
        List<House> houses=houseService.findAllHouse();
        Map<String,Object> result=new HashMap<>();
        result.put("result", houses);
        result.put("success", true);
        result.put("error", null);
        return result;
    }

    //搜索房源信息,模糊查询，已经过测试
    @RequestMapping("selectHouse")
    @ResponseBody
    public Map<String, Object> selectHouse(House house, @RequestParam(defaultValue = "1") Integer pageNum) {
        PageInfo<House> pageInfo = houseService.selectHouse(house, pageNum);
        Map<String,Object> result=new HashMap<>();
        if (pageInfo.getList().size() > 0) {
            result.put("result", pageInfo);
            result.put("success", true);
            result.put("error", null);
            result.put("message","添加成功");
            return result;
        }
        result.put("success", false);
        result.put("message","抱歉，暂未查找到相应结果");
        return result;
    }

    //查看房源信息，根据房源id精确查找，已经过测试
    @RequestMapping("findHouseById")
    @ResponseBody
    public Map<String, Object> findById(Integer id) {
        House house = houseService.findHouseById(id);
        Map<String, Object> result = new HashMap<>();
        if (house != null) {
            result.put("result", house);
            result.put("success", true);
            result.put("error", null);
            result.put("message", "搜索成功");
            return result;
        }
        result.put("success", false);
        result.put("message", "查找失败");
        return result;
    }

    //添加房源信息，已经过测试
    @RequestMapping("addHouse")
    @ResponseBody
    public Map<String,Object> addHouse(House house,String ticket){
        Map<String,Object> result=new HashMap<>();
        //1.拿id
        int id = userService.getUID(ticket);
        if(house.getId()==null){
            house.setUserId(id);
            houseService.addHouse(house);
            result.put("success", true);
            result.put("error", null);
            result.put("message","添加成功");
            return result;
        }
        result.put("success", false);
        result.put("message","添加失败");
        return result;
    }

    //收藏夹，用户收藏房源，已测试
    @RequestMapping("addCollection")
    @ResponseBody
    public Map<String, Object> addCollection(Integer houseId,String ticket) {//前端隐藏域传用户点击收藏的房子的id，name=houseId
        Map<String, Object> result = new HashMap<>();

        //1.根据票据查id;
        int id = userService.getUID(ticket);

        //2.根据id查全部数据
        User user = new User();
        user.setId(id);
        User user1 = userService.getUser(user);

        if (houseId != null) {

            user1.setHouseId(houseId);
            userService.addCollection(user1);
            result.put("success", true);
            result.put("error", null);
            result.put("message", "收藏成功");
            return result;
        }
        result.put("success", false);
        result.put("message", "收藏失败");
        return result;
    }

    //展示收藏夹，展示用户收藏的内容，已测试
    @RequestMapping("findCollection")
    @ResponseBody
    public Map<String, Object> findCollection(String ticket) {
        Map<String, Object> result = new HashMap<>();

        //1.根据票据查id;
        int id = userService.getUID(ticket);

        if (id != 0) {
            List<House> houses = userService.findCollection(id);
            result.put("result", houses);
            result.put("success", true);
            result.put("error", null);
            result.put("message", "查找成功");
            return result;
        }
        result.put("success", false);
        result.put("message", "查找失败");
        return result;
    }

    //用户浏览记录，将浏览记录保存到数据库中
    @RequestMapping("addBrowse")
    @ResponseBody
    public Map<String, Object> addBrowse(Integer houseId,String ticket) {//前端隐藏域传用户点击过的房子的id，name=houseId
        Map<String, Object> result = new HashMap<>();

        //1.根据票据查id;
        int id = userService.getUID(ticket);

        //2.根据id查全部数据
        User user = new User();
        user.setId(id);
        User user1 = userService.getUser(user);

        if (houseId != null) {

            user1.setHouseId(houseId);
            userService.addBrowse(user1);
            result.put("success", true);
            result.put("error", null);
            result.put("message", "添加成功");
            return result;
        }
        result.put("success", false);
        result.put("message", "添加失败");
        return result;
    }

    //用户点击浏览记录，将用户查看过的房子的按照时间的降序顺序从数据库中回显出来
    @RequestMapping("findBrowse")
    @ResponseBody
    public Map<String, Object> findBrowse(String ticket) {
        Map<String, Object> result = new HashMap<>();

        //1.根据票据查id;
        int id = userService.getUID(ticket);

        if (id != 0) {
            List<House> houses = userService.findBrowse(id);
            result.put("result", houses);
            result.put("success", true);
            result.put("error", null);
            result.put("message", "查找成功");
            return result;
        }
        result.put("success", false);
        result.put("message", "查找失败");
        return result;
    }

}
