package com.example.sell.demo.service.impl;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 15:47
 */

import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.service.OrderService;
import com.example.sell.demo.service.PayService;
import com.example.sell.demo.untils.JsonUntil;
import com.example.sell.demo.untils.Mathuntil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private  BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;
    private static  final  String Oed="微信点餐订单";

    @Override
    public PayResponse create(OrderDto orderDto) {
        PayRequest payRequest=new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderName(Oed);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId(orderDto.getOrderId());
        log.info("【微信支付】发起在支付，request={}", JsonUntil.toJSon(payRequest));
        PayResponse response=bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，response={}",JsonUntil.toJSon(payRequest));
        return response;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付状态
        //3.支付金额
        //4.支付人
       PayResponse payResponse= bestPayService.asyncNotify(notifyData);
       log.info("【微信支付】异步通知，payresponse={}",JsonUntil.toJSon(payResponse));
       //修改订单状态
        OrderDto orderDto=orderService.findOne(payResponse.getOrderId());
        if(orderDto==null){
            log.error("【微信支付】 订单不存在");
            throw  new SellException(ResultEmuns.ORDER_NOT_EXIT);
        }
        if(!Mathuntil.equesls(orderDto.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】 订单金额不一致,orderId={},微信通知金额={},系统金额={}",orderDto.getOrderId(),payResponse.getOrderAmount(),orderDto.getOrderAmount());
            throw  new SellException(ResultEmuns.WECHAT_TON_ERR);
        }
        orderService.paid(orderDto);
       return payResponse;
    }

    @Override
    public RefundResponse refoud(OrderDto orderDto) {
        RefundRequest refundRequest=new RefundRequest();
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 request={}",JsonUntil.toJSon(refundRequest));
        RefundResponse refundResponse=bestPayService.refund(refundRequest);
        log.info("【微信退款】 response={}",JsonUntil.toJSon(refundResponse));
        return  refundResponse;
    }
}
