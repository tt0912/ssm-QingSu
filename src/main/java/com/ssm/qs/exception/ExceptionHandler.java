package com.ssm.qs.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *  Author 田宇
 *  Date   2018/1/20 0020 13:14
 *  Description 全局异常处理,遇到else就抛出异常
 */

public class ExceptionHandler implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        if(httpServletResponse.isCommitted()){
            return null;
        }
        //所有的请求都是ajax
        //if(isAjax(httpServletRequest)){
            Map<String,Object> result = new HashMap<>();
            result.put("status",false);
            result.put("error",e.getMessage());
            //配置json格式转换
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            return new ModelAndView(jsonView,result);
    }
}
