package com.example.sell.demo.service;

import com.example.sell.demo.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {
    PayResponse create(OrderDto orderDto);
    PayResponse  notify(String notifyData);
    RefundResponse refoud(OrderDto orderDto);
}
