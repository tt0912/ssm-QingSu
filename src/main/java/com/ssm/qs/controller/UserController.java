package com.ssm.qs.controller;

import com.ssm.qs.pojo.Info;
import com.ssm.qs.pojo.Phone;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.PhoneService;
import com.ssm.qs.service.UserService;
import com.ssm.qs.util.Sendsms;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private PhoneService phoneService;
    //声明结果容器
    Map<String,Object> result = new HashMap<>();

    //1.发送验证码
    @RequestMapping("/send_login_code.html")
    @ResponseBody
    public Map<String, Object> sendsms(@RequestBody Map<String,String> param) throws Exception {

        String phone = param.get("phone");
        if(phone==null || phone==""){
            throw new Exception("请输入手机号");
        }
        //1.1 发送验证码
        Map<String,String> map = Sendsms.send(phone);
        String code = map.get("code");//状态码
        String mobileCode = map.get("mobile_code");//验证码

        //1.2 手机号+验证码存表
        System.out.println(code);
        System.out.println(mobileCode);
        Phone phone1 = new Phone();
        phone1.setPhone(phone);
        phone1.setCode(mobileCode);
        phoneService.addPhone(phone1);

        //1.3 判断是否发送成功
        if("2".equals(code)){//验证码发送成功
            result.put("success",true);//请求成功
            result.put("result",true);//发送成功
            result.put("error",null);
        }else{//发送失败，手动抛出异常，全局异常进行处理
            throw new Exception("服务器繁忙");
        }
        return result;
    }

    //2.验证码登录
    @RequestMapping("/code_login.html")
    @ResponseBody
    public Map<String, Object> codeLogin(@RequestBody Map<String,String> param, HttpSession session) throws Exception {

        String phone = param.get("phone");
        String code = param.get("code");
        //1.二次校验
        if(phone==null || phone==""){
            throw new Exception("必须输入手机号");
        }
        if(code==null || code==""){
            throw new Exception("请输入验证码！");
        }
        //2.验证码验证
        long count = checkCode(phone,code);
        if (count>0) {//验证通过
            //根据手机号查出用户信息
            User user = new User();
            user.setPhone(phone);
            User user_find = userService.getUser(user);

            if (user_find == null) {//没有该用户信息，注册
                String name = "用户" + phone.substring(7);//用户6012
                user.setUserName(name);
                user.setNickName(name);
                user.setRealName(name);
                session.setAttribute("SESSION_USER",user);
                result.put("result",user);

            }else{//有用户信息，直接登录
                //id = user_find.getId();
                session.setAttribute("SESSION_USER",user_find);
                result.put("result",user_find);
            }
            //时间戳票据对应id插入数据库(废弃)
            /*String ticket = System.currentTimeMillis()+"";
            Info info = new Info();
            info.setTicket(ticket);
            info.setUid(id);
            userService.addTicket(info);*/

            //4.返回结果
            result.put("success",true);//请求成功
            result.put("error",null);
            return result;
        }else{
            throw new Exception("验证码输入错误");
        }
    }


    //3.密码登录(用户名，邮箱，手机号)
    @RequestMapping("/pwd_login.html")
    @ResponseBody
    public Map<String,Object> pwdLogin(@RequestBody User user,HttpSession session) throws Exception{

        //1.校验
        if(user.getUserName()==null || user.getUserName()==""){
            throw new Exception("用户名不能为空！");
        }
        if(user.getPassword()==null || user.getPassword()==""){
            throw new Exception("密码不能为空！");
        }
        //2.查数据库
        User user_find = userService.getUser(user);
        if(user_find!=null){//登录成功
            /*String ticket = System.currentTimeMillis()+"";
            Info info = new Info();
            info.setTicket(ticket);
            info.setUid(user1.getId());
            userService.addTicket(info);*/
            session.setAttribute("SESSION_USER",user_find);

            //4.返回结果
            result.put("success",true);//请求成功
            result.put("result",user_find);
            result.put("error",null);
            return result;
        }else{//登陆失败
            throw new Exception("用户名或密码错误");
        }
    }


    //4.忘记密码(设置新密码)
    @RequestMapping("/forget_pwd.html")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Map<String,String> param) throws Exception {

        String phone = param.get("phone");
        String code = param.get("code");
        String password = param.get("password");

        if(phone==null || phone==""){
            throw new Exception("必须输入手机号");
        }
        if(code==null || code==""){
            throw new Exception("请输入验证码！");
        }
        if(password==null || password==""){
            throw new Exception("必须输入新密码");
        }

        long count = checkCode(phone,code);
        if(count>0) {//验证通过
            User user = new User();
            user.setPhone(phone);//根据手机号修改密码，同样是唯一标识
            user.setPassword(password);
            userService.updateUser(user);
        }else {
            throw new Exception("验证码错误");
        }
        result.put("success",true);
        result.put("result",true);
        result.put("error",null);
        return result;
    }


    //5.退出登录
    @RequestMapping("/logout.html")
    @ResponseBody
    public Map<String, Object> delete(HttpSession session) {

        /*//1.删票据(废弃)
        //userService.deleteTicket(ticket);*/
        //1.session失效
        session.invalidate();
        //2.返回结果
        result.put("success",true);
        result.put("result","退出成功");
        result.put("error",null);
        return result;
    }



    //6.获取用户信息:
    @RequestMapping("/show.html")
    @ResponseBody
    public Map<String, Object> showInfo(HttpSession session) {

        /*1.根据票据查id;
        int id = userService.getUID(ticket);
        2.根据id查全部数据
        User user1 = new User();
        user1.setId(id);
        User user = userService.getUser(user1);*/
        User user_find = (User) session.getAttribute("SESSION_USER");

        result.put("success", true);
        result.put("result", user_find);
        result.put("error", null);
        return result;
    }



    //7.修改头像
    @RequestMapping("/upload_headUrl.html")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam(value = "file") MultipartFile file) {

        //上传图片写入本地
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
    public Map<String, Object> uodate(@RequestBody User user,HttpSession session) throws Exception{

        if(user!=null){
            //1.查phone
            User session_user = (User) session.getAttribute("SESSION_USER");
            String phone = session_user.getPhone();
            //2.根据电话去更新用户的信息
            user.setPhone(phone);
            userService.updateUser(user);
            //3.返回新的用户信息
            User newUser = userService.getUser(user);
            session.setAttribute("SESSION_USER",newUser);
            //4.返回结果
            result.put("success", true);
            result.put("result", newUser);
            result.put("error", null);
            return result;
        }else {
            throw new Exception("修改失败");
        }
    }


    //验证验证码
    public long checkCode(String phone, String code){

        Phone phoneDemo = new Phone();
        phoneDemo.setPhone(phone);
        phoneDemo.setCode(code);
        long count = phoneService.checkCode(phoneDemo);
        return count;
    }
}
