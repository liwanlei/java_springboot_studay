package com.example.sell.demo.VO;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 13:25
 */

import lombok.Data;

@Data
public class ResultVO <T>{
    private  Integer code;
    private String msg;
    private  T data;
}
