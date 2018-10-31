package com.example.sell.demo.dto;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 16:25
 */

import lombok.Data;

@Data
public class CartDTO {
    private  String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
