package com.example.sell.demo.daoobject;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 12:37
 */

import com.example.sell.demo.enmus.ProductStatysEmuns;
import com.example.sell.demo.untils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    private  String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private  String productDescription;
    private  String productIcon;
    private  Integer categoryType;
    private  Integer productStatus;
    @JsonIgnore
    public ProductStatysEmuns getproduct(){
        return EnumUtil.getByCode(productStatus,ProductStatysEmuns.class);
    }
}
