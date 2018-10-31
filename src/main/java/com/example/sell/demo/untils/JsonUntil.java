package com.example.sell.demo.untils;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 16:12
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUntil {
    public  static  String toJSon(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return  gson.toJson(object);
    }
}
