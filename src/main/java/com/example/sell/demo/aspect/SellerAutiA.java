package com.example.sell.demo.aspect;/*
 *
 * @author lileilei
 * Date: 2018/10/26
 * Time: 10:53
 */

import com.example.sell.demo.exception.SellAeeException;
import com.example.sell.demo.untils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
@Aspect
@Component
@Slf4j
public class SellerAutiA {
    @Pointcut("execution(public * com.example.sell.demo.controller.Sell*.*(..))")
    public void verify(){

    };
//    @Before("verify()")
//    public void doverify(){
//        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request=attributes.getRequest();
//        Cookie cookie= CookieUtil.get(request,"token");
//        if(cookie==null){
//            log.warn("【登录校验】cookie中查询不到token");
//            throw  new SellAeeException();
//        }
//    };
}
