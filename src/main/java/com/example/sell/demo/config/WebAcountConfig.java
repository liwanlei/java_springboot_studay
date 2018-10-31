package com.example.sell.demo.config;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 14:48
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WebAcountConfig {
    private String mpAppid;
    private  String mpAppsect;
    private  String mchId;
    private  String mchKey;
    private  String keyPath;
    private  String notifyUrl;
}
