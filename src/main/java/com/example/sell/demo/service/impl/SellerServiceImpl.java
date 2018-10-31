package com.example.sell.demo.service.impl;/*
 *
 * @author lileilei
 * Date: 2018/10/26
 * Time: 10:44
 */

import com.example.sell.demo.daoobject.SellerInfo;
import com.example.sell.demo.repository.SellerInfoRepository;
import com.example.sell.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository repository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return  repository.findByOpenid(openid);
    }
}
