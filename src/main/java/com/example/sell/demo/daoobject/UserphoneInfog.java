package com.example.sell.demo.daoobject;/*
 *
 * @author lileilei
 * Date: 2018/10/29
 * Time: 14:11
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Slf4j
@Data
@Entity
public class UserphoneInfog {
    /*
    埋点
     */
    @Id
    private String userid;
    private  String ip;
    private Date usetime;
    private  String agent;
    private  String systemversion;
    private  String systemname;
}
