package com.ssm.qs.interceptor;

import com.ssm.qs.dao.UserDao;
import com.ssm.qs.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.判断session,看看是不是已登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("SESSION_USER");
        if(user!=null){
            return true;
        }
        //2.没保存用户状态，看看有没有票据
        String ticket = request.getParameter("ticket");
        if(ticket!=null && ticket!=""){
            //3.查数据库中有没有
            int uid = userDao.getUID(ticket);
            if (uid>0){
                User user1 = new User();
                user1.setId(uid);
                User user2 = userDao.getUser(user1);
                session.setAttribute("SESSION_USER",user2);
                return true;
            }
        }
        response.getWriter().write("请登录");
        return false;
    }
}
