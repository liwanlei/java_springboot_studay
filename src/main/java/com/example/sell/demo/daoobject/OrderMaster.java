package com.example.sell.demo.daoobject;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 14:39
 */

import com.example.sell.demo.enmus.OrderPayEm;
import com.example.sell.demo.enmus.OrderSatus;
import com.example.sell.demo.enmus.ProductStatysEmuns;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    //订单id
    @Id
    private  String orderId;
    //购买人名字
    private  String  buyerName;
    //手机号
    private  String buyerPhone;
    //地址
    private  String buyerAddress;
    //openid
    private  String buyerOpenid;
    //订单金额
    private BigDecimal orderAmount;
    private  Integer orderStatus= OrderSatus.NEW.getCode();
    private  Integer payStatus= OrderPayEm.WAIT.getCode();
    private Date createTime;
    private  Date updateTime;

}
