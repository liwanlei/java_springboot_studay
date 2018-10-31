package com.example.sell.demo.daoobject;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 14:55
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
@DynamicUpdate
@Data
public class OrderDetail {
    @Id
    private  String detailId;
    private  String orderId;
    private  String productId;
    private  String productName;
    private BigDecimal productPrice;
    private  Integer productQuantity;
    private  String productIcon;
}
