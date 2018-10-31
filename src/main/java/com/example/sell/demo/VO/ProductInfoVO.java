package com.example.sell.demo.VO;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 13:50
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {
    @JsonProperty("id")
    /*
    商品id
     */
    private  String productId;
    @JsonProperty("name")
    private  String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private  String productDescription;
    @JsonProperty("icon")
    private  String productIcon;
}
