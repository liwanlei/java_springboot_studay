package com.example.sell.demo.dto;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 15:32
 */

import com.example.sell.demo.daoobject.OrderDetail;
import com.example.sell.demo.enmus.OrderPayEm;
import com.example.sell.demo.enmus.OrderSatus;
import com.example.sell.demo.untils.EnumUtil;
import com.example.sell.demo.untils.sellialer.Date2LongSer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    @Id
    private  String orderId;
    private  String  buyerName;
    private  String buyerPhone;
    private  String buyerAddress;
    private  String buyerOpenid;
    private BigDecimal orderAmount;
    private  Integer orderStatus;
    private  Integer payStatus;
    @JsonSerialize(using = Date2LongSer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSer.class)
    private  Date updateTime;
    List<OrderDetail> orderDetailList;
    @JsonIgnore
    public  OrderSatus getOrderStatus(){
        return EnumUtil.getByCode(orderStatus,OrderSatus.class);
    };
    @JsonIgnore
    public OrderPayEm  getPayStatus(){
        return  EnumUtil.getByCode(payStatus,OrderPayEm.class);
    };
}
