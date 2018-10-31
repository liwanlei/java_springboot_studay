package com.example.sell.demo.converter;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 10:37
 */

import com.example.sell.demo.daoobject.OrderDetail;
import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2ToOrderDto {
    public  static OrderDto convent(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try{
            orderDetailList=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换错误】,String={}",orderForm.getItems());
            throw new SellException(ResultEmuns.PARM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return  orderDto;
    }
}
