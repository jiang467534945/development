package com.platform.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @Nullable Object o, Exception e) {

        logger.error("================================捕获异常开始===============================================");

        //handler为当前处理器适配器执行的对象
        String message = null;
        ModelAndView mv = new ModelAndView();

        if(e instanceof AuthorizationException) {
            mv.setViewName("/error/power.jsp");
        } else {
            mv.setViewName("/error/500.jsp");
        }

        //判断是否为系统自定义异常。
//        if(e instanceof CommonException) {
//            message = ((CommonException) e).getMessage();
//        } else {
//            message = "系统出错啦，稍后再试试！";
//        }
//
//
//
//        //跳转到相应的处理页面
//        mv.addObject("errorMsg", message);
        e.printStackTrace();

        logger.error("================================捕获异常结束===============================================");

        return mv;
    }
}
