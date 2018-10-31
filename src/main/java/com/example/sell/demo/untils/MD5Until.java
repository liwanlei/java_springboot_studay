package com.example.sell.demo.untils;/*
 *
 * @author lileilei
 * Date: 2018/10/31
 * Time: 10:19
 */
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Until {
    public  static String md5(String text) throws Exception{
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(md5.digest(text.getBytes()));
        return newstr;
    }
    public  static  boolean checkoutpassword(String password,String oldpassword) throws Exception{
        if(md5(password).equals(oldpassword)){
            return true;
        }
        return false;
    }

}
