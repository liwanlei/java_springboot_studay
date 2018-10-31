package com.example.sell.demo.service.impl;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 13:25
 */

import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.service.BuyerService;
import com.example.sell.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class BuyerSerivceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDto findOrder(String openid, String orderid) {
       return  checkout(openid,orderid);
    }

    @Override
    public OrderDto cacelOrder(String openid, String orderid) {
        OrderDto orderDto=checkout(openid,orderid);
        if(orderDto==null){
            log.error("【取消订单】 查不到改订单,orderid={}",orderid);
            throw  new  SellException(ResultEmuns.ORDER_NOT_EXIT);
        }
        return orderService.cancel(orderDto);
    }

    private  OrderDto checkout(String openid, String orderid){
        OrderDto orderDto=orderService.findOne(orderid);
        if(orderDto==null){
            return null;
        }
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】 订单的openid不一致 openid={},orderDto={}",openid,orderDto);
            throw new SellException(ResultEmuns.ORDER_USER_ERROR);
        }
        return orderDto;
    }
}
