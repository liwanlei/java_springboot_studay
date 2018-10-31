package com.example.sell.demo.enmus;

import lombok.Getter;

@Getter
public enum OrderPayEm implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;
    private  Integer code;
    private  String message;

    OrderPayEm(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
