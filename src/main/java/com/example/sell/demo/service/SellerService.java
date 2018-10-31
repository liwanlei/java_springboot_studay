package com.example.sell.demo.service;

import com.example.sell.demo.daoobject.SellerInfo;

public interface SellerService  {
    SellerInfo findSellerInfoByOpenid(String openid);
}
