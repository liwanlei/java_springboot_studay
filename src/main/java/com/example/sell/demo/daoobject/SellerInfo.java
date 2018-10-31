package com.example.sell.demo.daoobject;/*
 *
 * @author lileilei
 * Date: 2018/10/26
 * Time: 10:36
 */

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class SellerInfo {
    @Id
    private  String sellId;
    private  String username;
    private  String password;
    private  String openid;
}
