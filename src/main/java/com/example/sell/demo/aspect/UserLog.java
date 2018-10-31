package com.example.sell.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class UserLog {
    @Pointcut("execution(public * com.example.sell.demo.controller.*.*(..))")
    public void verify(){

    };
    @Before("verify()")
    public void doverify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("【用户请求数据】{}",request.getLocalAddr());
        log.info("【用户请求数据】{}",request.getRemoteAddr());
        log.info("【用户请求数据】{}",request.getParameterMap());
    };
}
