package com.ssm.qs.controller;

import com.ssm.qs.pojo.Info;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.UserService;
import com.ssm.qs.util.Sendsms;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  Author 田宇
 *  Date   2018/1/11 0011 10:13
 *  Description
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    //声明结果容器
    Map<String,Object> result = new HashMap<>();
    //回调验证码
    String mobileCode = null;

    /**
     * @param phone
     * @return
     * @throws Exception
     */
    //1.发送验证码
    @RequestMapping("/send_login_code.html")
    @ResponseBody
    public Map<String, Object> sendsms(String phone) throws Exception {

        //1.1 发送验证码
        Map<String,String> map = Sendsms.send(phone);
        String code = map.get("code");//状态码
        mobileCode = map.get("mobile_code");//验证码

        //1.2 验证码存表
        System.out.println(code);
        System.out.println(mobileCode);

        if("2".equals(code)){//验证码发送成功
            result.put("success",true);//请求成功
            result.put("result",true);
            result.put("error",null);
        }else{//发送失败，手动抛出异常，全局异常进行处理
            throw new Exception("服务器繁忙");
        }
        return result;
    }

    //2.验证码登录(局部)(已测试)
    @RequestMapping("/code_login.html")
    @ResponseBody
    public Map<String, Object> codeLogin(String phone, String code) throws Exception {

        //1.二次校验
        if(phone==null || phone==""){
            throw new Exception("必须输入手机号");
        }
        if(code==null || code==""){
            throw new Exception("请输入验证码！");
        }
        if ("123".equals(code)) {//验证通过
            //1.查
            User user = new User();
            user.setPhone(phone);
            User user_find = userService.getUser(user);//查出的用户信息
            int id = 0;

            if (user_find == null) {//2.1 没有该用户信息，注册
                String name = "用户" + phone.substring(7);//用户6012
                user.setUserName(name);
                user.setNickName(name);
                user.setRealName(name);
                id = userService.addUser(user);//插入成功，返回id
            }else{//2.1 直接登录
                id = user_find.getId();
            }
            //3.票据对应id插入数据库
            String ticket = UUID.randomUUID().toString().replaceAll("-","");
            Info info = new Info();
            info.setTicket(ticket);
            info.setUid(id);
            userService.addTicket(info);

            //4.返回结果
            result.put("success",true);//请求成功

            Map<String,Object> map = new HashMap<>();
            map.put("ticket",ticket);
            map.put("uid",id);

            result.put("result",map);
            result.put("error",null);
            return result;
        }else{
            throw new Exception("验证码输入错误");
        }
    }


    //3.密码登录(用户名，邮箱，手机号)(已测试)
    @RequestMapping("/pwd_login.html")
    @ResponseBody
    public Map<String,Object> pwdLogin(User user) throws Exception{

        //1.校验
        if(user.getUserName()==null && user.getUserName()==""){
            throw new Exception("用户名不能为空！");
        }
        if(user.getPassword()==null && user.getPassword()==""){
            throw new Exception("密码不能为空！");
        }
        //2.查数据库
        User user1 = userService.getUser(user);
        if(user1!=null){
            //3.票据对应id插入数据库
            String ticket = UUID.randomUUID().toString().replaceAll("-","");
            Info info = new Info();
            info.setTicket(ticket);
            info.setUid(user1.getId());
            userService.addTicket(info);

            //4.返回结果
            result.put("success",true);//请求成功

            Map<String,Object> map = new HashMap<>();
            map.put("ticket",ticket);
            map.put("uid", user1.getId());//对应用户id

            result.put("result",map);
            result.put("error",null);
            return result;
        }else{
            throw new Exception("用户名或密码错误");
        }
    }


    //4.忘记密码(设置新密码)(已测试)
    @RequestMapping("/forget_pwd.html")
    @ResponseBody
    public Map<String, Object> update(String phone, String code, String password) throws Exception {

        if(phone==null || phone==""){
            throw new Exception("必须输入手机号");
        }
        if(code==null || code==""){
            throw new Exception("请输入验证码！");
        }
        if(password==null || password==""){
            throw new Exception("必须输入新密码");
        }
        if(mobileCode.equals(code)) {//验证通过
            User user = new User();
            user.setPhone(phone);//根据手机号修改密码，同样是唯一标识
            user.setPassword(password);
            userService.updateUser(user);
        }else {
            throw new Exception("验证码输入错误");
        }
        result.put("success",true);
        result.put("result",true);
        result.put("error",null);
        return result;
    }


    //5.退出登录(删数据库票据)(已测试)
    @RequestMapping("/logout.html")
    @ResponseBody
    public Map<String, Object> delete(String ticket) {

        //1.删票据
        userService.deleteTicket(ticket);
        //2.返回结果
        result.put("success",true);
        result.put("result","退出成功");
        result.put("error",null);
        return result;
    }

    //6.获取用户信息:
    @RequestMapping("/show.html")
    @ResponseBody
    public Map<String, Object> showInfo(String ticket) {

        //1.根据票据查id;
        int id = userService.getUID(ticket);

        //2.根据id查全部数据
        User user1 = new User();
        user1.setId(id);
        User user = userService.getUser(user1);
        System.out.println(user);

        result.put("success", true);
        result.put("result", user);
        result.put("error", null);
        return result;
    }

    //7.修改头像
    @RequestMapping("/upload_headUrl.html")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File("E:/upload", file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("success", true);
        result.put("result", file.getOriginalFilename());
        result.put("error", null);
        return result;
    }


    //8.修改用户信息
    @RequestMapping("/update.html")
    @ResponseBody
    public Map<String, Object> uodate(String ticket, User user) {

        //1.查id
        int id = userService.getUID(ticket);
        //2.更新
        user.setId(id);
        userService.updateUser(user);
        User user1 = userService.getUser(user);
        //3.返回结果
        result.put("success", true);
        result.put("result", user1);
        result.put("error", null);
        return result;
    }

}
