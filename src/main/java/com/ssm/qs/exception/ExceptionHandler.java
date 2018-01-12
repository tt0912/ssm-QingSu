package com.ssm.qs.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        String msg = e.getMessage();
        if(httpServletResponse.isCommitted()){
            return null;
        }
        if(isAjax(httpServletRequest)){
            Map<String,Object> result = new HashMap<>();
            result.put("status",false);
            result.put("message",e.getMessage());
            //配置json格式转换
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            return new ModelAndView(jsonView,result);
        }else {
            return new ModelAndView("error","message",msg);
        }
    }
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null;
    }
}
