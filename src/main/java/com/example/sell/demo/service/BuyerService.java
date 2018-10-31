package com.example.sell.demo.service;

import com.example.sell.demo.dto.OrderDto;

public interface BuyerService {
    OrderDto findOrder(String openid,String orderid);
    OrderDto cacelOrder(String openid,String orderid);
}
