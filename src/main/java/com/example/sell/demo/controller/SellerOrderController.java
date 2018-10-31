package com.example.sell.demo.controller;
import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
/*
卖家端看到的订单的详情
 */
@Controller
@Slf4j
@RequestMapping("/sell/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size" ,defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<OrderDto> orderDtos=orderService.findList(pageRequest);
        map.put("orderDaopage",orderDtos);
        map.put("curetPage",page);
        return  new ModelAndView("order/list",map);
    }
    @GetMapping("/cancel")
    public  ModelAndView cancel(@RequestParam("orderId") String orderId,
                                Map<String ,Object> map){
        OrderDto orderDto=new  OrderDto();
        try {
            orderDto = orderService.findOne(orderId);
            orderService.cancel(orderDto);
        }
        catch (SellException e){
            log.info("【卖家端取消订单】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEmuns.SUCCESS_OREDE_CANEL.getMessage());
        map.put("url","/sell/order/list");
        return new ModelAndView("common/success");
    }
    /*
    卖家查看订单详情
     */
    @GetMapping("/orderdetail")
    public  ModelAndView detail(@RequestParam("orderId") String orderId,
                                Map<String ,Object> map){
        OrderDto orderDto=new OrderDto();
        try{
            orderDto=orderService.findOne(orderId);//通过id查询订单
        }catch (SellException e){
            //发生异常要收集异常
            log.info("【卖家端订单详情】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDto",orderDto);
        return  new ModelAndView("order/detail",map);
    }
    /*
    完结订单
     */
    @GetMapping("/finsh")
    public  ModelAndView finsh(@RequestParam("orderId") String orderId,
                               Map<String ,Object> map){
        try{
            OrderDto orderDto=orderService.findOne(orderId);
            orderService.finish(orderDto);
        }catch (SellException e){
            log.info("【卖家端完结订单】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEmuns.SUCCESS_OREDE_FINSH.getMessage());
        map.put("url","/sell/order/list");
        return new ModelAndView("common/success");
    }
}