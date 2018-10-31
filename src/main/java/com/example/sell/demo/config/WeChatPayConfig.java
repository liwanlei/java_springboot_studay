package com.example.sell.demo.config;/*
 *
 * @author lileilei
 * Date: 2018/10/24
 * Time: 15:56
 */

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WeChatPayConfig {
    @Autowired
    private  WebAcountConfig acountConfig;
    @Bean
    public BestPayServiceImpl bestPayService(){
        WxPayH5Config wxPayH5Config=new WxPayH5Config();
        wxPayH5Config.setAppId(acountConfig.getMpAppid());
        wxPayH5Config.setAppSecret(acountConfig.getMpAppsect());
        wxPayH5Config.setKeyPath(acountConfig.getKeyPath());
        wxPayH5Config.setMchId(acountConfig.getMchId());
        wxPayH5Config.setNotifyUrl(acountConfig.getNotifyUrl());
        BestPayServiceImpl bestPayService=new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }
}
