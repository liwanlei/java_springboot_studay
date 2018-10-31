package com.example.sell.demo.untils;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 16:07
 */

import java.util.Random;

public class KeyUntil  {
    //生成唯一
  public  static synchronized String getUniquekey(){
      Random random=new Random();

      Integer i=random.nextInt(900000)+100000;
      return System.currentTimeMillis()+String.valueOf(i);
  }
}
