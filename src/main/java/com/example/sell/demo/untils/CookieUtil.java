package com.example.sell.demo.untils;/*
 *
 * @author lileilei
 * Date: 2018/10/26
 * Time: 11:16
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public  static  void set(HttpServletResponse response,
                             String name,
                             String value,
                             int maxAge){
        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public  static  Cookie  get(HttpServletRequest request,
                              String name){
        Map<String,Cookie> cookieMap=redCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }
    private  static Map<String ,Cookie> redCookieMap(HttpServletRequest request){
        Map<String ,Cookie> cookieMap=new HashMap<>();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
