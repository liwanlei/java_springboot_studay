package com.example.sell.demo.controller;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 15:41
 */

import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.service.OrderService;
import com.example.sell.demo.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @GetMapping("/creat")
    public ModelAndView creat(@RequestParam("orderId") String orderId,
                              @RequestParam("returnUrl") String returnUrl,
                              Map<String,Object> map){
        //查询订单
        OrderDto orderDto=orderService.findOne(orderId);
        if(orderDto==null){
            throw new SellException(ResultEmuns.ORDER_NOT_EXIT);
        }
        //发起在支付
        PayResponse payResponse=payService.create(orderDto);
        map.put("payResonse",payResponse);
        map.put("returnUrl",returnUrl);
        return  new ModelAndView("pay/create",map);
    }
    /*
    微信异步通知
     */
    @PostMapping("/notify")
    public  ModelAndView  notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        //返回给微信处理结果
        return  new ModelAndView("pay/success");
    }
}
