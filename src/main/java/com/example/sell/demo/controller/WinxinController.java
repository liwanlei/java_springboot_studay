package com.example.sell.demo.controller;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 14:00
 */

import com.sun.org.glassfish.gmbal.ParameterNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/weixin")
public class WinxinController {
    @GetMapping("/auth")
    public  void auth(@RequestParam("code") String code){
      log.info("进入auth方法");
      String url="";
        RestTemplate restTemplate=new RestTemplate();
         String response=restTemplate.getForObject(url,String.class);
    }
}
