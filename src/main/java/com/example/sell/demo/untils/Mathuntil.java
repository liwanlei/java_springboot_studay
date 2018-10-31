package com.example.sell.demo.untils;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 17:30
 */

public class Mathuntil {
    public  static  boolean equesls(double d1,double d2){
        double result=Math.abs(d1-d2);
        if(result<0.01){
            return true;
        }else {
            return false;
        }
    }
}
