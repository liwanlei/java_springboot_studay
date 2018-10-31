package com.example.sell.demo.VO;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 13:41
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {
    @JsonProperty("name")
    private  String categoryname;
    @JsonProperty("type")
    private  Integer categorytype;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
