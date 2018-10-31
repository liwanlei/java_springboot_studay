package com.example.sell.demo.controller;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 9:56
 */

import com.example.sell.demo.VO.ResultVO;
import com.example.sell.demo.converter.OrderForm2ToOrderDto;
import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.form.OrderForm;
import com.example.sell.demo.service.BuyerService;
import com.example.sell.demo.service.OrderService;
import com.example.sell.demo.untils.ResultVOUntils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/creat")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确,orderform={}",orderForm);
            throw  new SellException(ResultEmuns.PARM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto= OrderForm2ToOrderDto.convent(orderForm);
//        System.out.println( orderDto.getOrderDetailList());
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】,购物车不能为空");
            throw  new SellException(ResultEmuns.CART_ERROR);
        }
        OrderDto createOr=orderService.create(orderDto);
        Map<String,String> map=new HashMap<>();
        map.put("orderid",createOr.getOrderId());
        return ResultVOUntils.success(map);
    }
    //订单列表
    @PostMapping("/list")
    public  ResultVO<List<OrderDto>> list(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page",defaultValue = "0") Integer page,
                                          @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEmuns.PARM_ERROR);
        }
        PageRequest request=new PageRequest(page,size);
        Page<OrderDto>orderDtoPage=orderService.findList(openid,request);
        //转存date-long
        return ResultVOUntils.success(orderDtoPage.getContent());
    }
    //订单详情
    @PostMapping("/detail")
    public  ResultVO<OrderDto> detail(@RequestParam("openid" ) String openid,
                                      @RequestParam("orderId") String orderId){
        OrderDto orderDto=buyerService.findOrder(openid,orderId);
        return  ResultVOUntils.success(orderDto);
    }
    //取消订单
    @PostMapping("/cancel")
    public  ResultVO cancel(@RequestParam("openid" ) String openid,
                            @RequestParam("orderId") String orderId){
        buyerService.cacelOrder(openid,orderId);
        return ResultVOUntils.success();
    }
}
