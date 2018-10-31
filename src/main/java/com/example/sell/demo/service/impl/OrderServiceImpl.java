package com.example.sell.demo.service.impl;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 15:39
 */

import com.example.sell.demo.converter.OrderMater2OrderDTO;
import com.example.sell.demo.daoobject.OrderDetail;
import com.example.sell.demo.daoobject.OrderMaster;
import com.example.sell.demo.daoobject.ProductInfo;
import com.example.sell.demo.dto.CartDTO;
import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.enmus.OrderPayEm;
import com.example.sell.demo.enmus.OrderSatus;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.repository.OrderDetailRepository;
import com.example.sell.demo.repository.OrderMasterRepository;
import com.example.sell.demo.service.OrderService;
import com.example.sell.demo.service.PayService;
import com.example.sell.demo.service.ProductService;
import com.example.sell.demo.service.WebSockets;
import com.example.sell.demo.untils.KeyUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PayService payService;
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        BigDecimal orderAmount=new BigDecimal(0);
        String  orderId= KeyUntil.getUniquekey();
        //1.查询商品 数量价格
        List<CartDTO> cartDTOList=new ArrayList<>();
        for (OrderDetail orderDetail:orderDto.getOrderDetailList()){
           ProductInfo productInfo= productService.findOne(orderDetail.getProductId());
           if(productInfo==null){
               throw  new SellException(ResultEmuns.PRODUCT_NOT_EXQIT);
           }
            //2.计算订单总价
            orderAmount=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
           //订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUntil.getUniquekey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
            CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        //3.写入订单数据库（ordermaster  orderdetail）
        OrderMaster orderMaster=new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderStatus(OrderSatus.NEW.getCode());
        orderMaster.setPayStatus(OrderPayEm.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        productService.decreateStock(cartDTOList);
        //发送websock消息
        WebSockets.sendMessage("你有新的订单");
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
      OrderMaster orderMaster=  orderMasterRepository.findById(orderId).get();
      if(orderMaster==null){
          throw  new  SellException(ResultEmuns.ORDER_NOT_EXIT);
      }
      List<OrderDetail> orderDetailList= orderDetailRepository.findByOrderId(orderId);
      if(CollectionUtils.isEmpty(orderDetailList)){
          throw  new  SellException(ResultEmuns.ORDER_DETAIL_E);
      }
      OrderDto orderDto=new OrderDto();
      BeanUtils.copyProperties(orderMaster,orderDto);
      orderDto.setOrderDetailList(orderDetailList);
      return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
      Page<OrderMaster> orderMasterPage=  orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
      List<OrderDto> orderDtoList=OrderMater2OrderDTO.convert(orderMasterPage.getContent());
      Page<OrderDto> orderDtoPage=new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster=new OrderMaster();

        //判断订单的状态
        if (!orderDto.getOrderStatus().equals(OrderSatus.NEW.getCode())){
            log.error("【取消订单】 订单状态不正确,orderid={},orderstatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new  SellException(ResultEmuns.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderSatus.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result=orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("【取消订单】 更新失败，orderMaster={}" ,orderMaster);
            throw new  SellException(ResultEmuns.ORDER_UPDATE_ERROR);
        }
        //返还库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单】 订单详情中没有商品，orederDto={}",orderDto);
            throw new SellException(ResultEmuns.ORDER_PRODUCT);
        }
        List<CartDTO> cartDTOList=orderDto.getOrderDetailList().stream().map(e ->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.increateStock(cartDTOList);
        //如果已经支付，退款
        if(orderDto.getPayStatus().equals(OrderPayEm.SUCCESS.getCode())){
            payService.refoud(orderDto);
        }
        return orderDto ;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderSatus.NEW.getCode())){
            log.error("【完结订单】 订单状态不对，orderid={},orderststus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new  SellException(ResultEmuns.ORDER_STATUS_ERROR);
        }

        //修改状态
        orderDto.setOrderStatus(OrderSatus.FINSHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result=orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("【完结订单】 更新失败，orderMaster={}" ,orderMaster);
            throw new  SellException(ResultEmuns.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderSatus.NEW.getCode())){
            log.error("【支付订单】 订单状态不对，orderid={},orderststus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new  SellException(ResultEmuns.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDto.getPayStatus().equals(OrderPayEm.WAIT.getCode())){
            log.error("【订单支付】订单支付状态不正确，OderDto={}",orderDto);
            throw  new  SellException(ResultEmuns.ORDER_PAY_ERROR);
        }

        //修改支付状态
        orderDto.setPayStatus(OrderPayEm.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result=orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("【订单支付】 更新失败，orderMaster={}" ,orderMaster);
            throw new  SellException(ResultEmuns.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterRepository.findAll(pageable);
        List<OrderDto> orderDtoList=OrderMater2OrderDTO.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDtoList,pageable,orderMasterPage.getTotalElements());
    }
}
