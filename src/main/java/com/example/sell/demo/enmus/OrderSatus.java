package com.example.sell.demo.enmus;

import lombok.Getter;

@Getter
public enum OrderSatus implements  CodeEnum {
    NEW(0,"新订单"),
    FINSHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;
    private  Integer code;
    private  String message;
    OrderSatus(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
