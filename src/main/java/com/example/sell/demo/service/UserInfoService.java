package com.example.sell.demo.service;

import com.example.sell.demo.daoobject.USerInfo;

public interface UserInfoService {
    USerInfo create(String username,String password);
    USerInfo create(String openid);
}
