package com.example.sell.demo.exception;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 15:52
 */

import com.example.sell.demo.enmus.ResultEmuns;
import lombok.Getter;

@Getter
public class SellException extends  RuntimeException {
    private  Integer code;
    public  SellException(ResultEmuns resultEmuns){
        super(resultEmuns.getMessage());
        this.code=resultEmuns.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
