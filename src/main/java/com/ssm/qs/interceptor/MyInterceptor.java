package com.ssm.qs.interceptor;

import com.ssm.qs.pojo.User;
import com.ssm.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Author 田宇
 *  Date   2018/1/20 0020 11:42
 *  Description 登陆拦截
 */
public class MyInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private UserService userService;//调用dao层拿用户完整信息放到session

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.判断用户是否登录
        String ticket = request.getParameter("ticket");
        if(ticket!=null && ticket!=""){
            int id = userService.getUID(ticket);
            if(id>0){
                User userDemo = new User();
                userDemo.setId(id);
                User user = userService.getUser(userDemo);

                //2.用户信息放session
                HttpSession session = request.getSession();
                session.setAttribute("SESSION_USER",user);
                return true;
            }
        }
        response.getWriter().write("{\"status\",false,\"message\",\"请登录\"}");
        return false;
    }
}
