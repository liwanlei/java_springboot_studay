package com.example.sell.demo.daoobject;/*
 *
 * @author lileilei
 * Date: 2018/10/30
 * Time: 14:17
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class USerInfo {
    @Id
    @GeneratedValue
    private  Integer id;
    private  String username;
    private  String password;
    private  String openid;
    private Date createTime;
    private  Date updateTime;
}
