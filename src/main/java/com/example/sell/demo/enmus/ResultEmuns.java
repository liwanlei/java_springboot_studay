package com.example.sell.demo.enmus;

import lombok.Getter;

@Getter
public enum ResultEmuns {
    SUCCESS_OREDER_CANEL(0,"成功"),
    PARM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXQIT(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDER_DETAIL_E(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_ERROR(15,"订单更新失败"),
    ORDER_PRODUCT(16,"订单详情为空"),
    ORDER_PAY_ERROR(17,"订单支付状态不正确"),
    CART_ERROR(18,"购物车不能为空"),
    ORDER_USER_ERROR(19,"订单不属于当前用户"),
    WECHAT_ERROR(20,"微信公众号方面异常"),
    WECHAT_TON_ERR(21,"微信异步通知金额校验不通过"),
    SUCCESS_OREDE_CANEL(22,"订单取消成功"),
    SUCCESS_OREDE_FINSH(23,"订单完结成功"),
    PRODUCT_STATUS(24,"商品状态不正确"),
    USER_NOUT_EXSITS(25,"用户不存在"),
    USER_IS_EXSITS(26,"用户已经存在"),
    USER_CREAT_SUCCESS(27,"用户创建成功"),
    USER_CREAT_ERROR(28,"用户创建过程中出错")
    ;
    private  Integer code;
    private  String message;

    ResultEmuns(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
