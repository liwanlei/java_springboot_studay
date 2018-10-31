package com.example.sell.demo.converter;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 17:24
 */

import com.example.sell.demo.daoobject.OrderMaster;
import com.example.sell.demo.dto.OrderDto;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;
public class OrderMater2OrderDTO {
    public  static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return  orderDto;
    }
    public  static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e->
                convert(e)).collect(Collectors.toList());
    }
}
