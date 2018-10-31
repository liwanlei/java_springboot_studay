package com.example.sell.demo.service.impl;/*
 *
 * @author lileilei
 * Date: 2018/10/31
 * Time: 9:09
 */

import com.example.sell.demo.daoobject.USerInfo;
import com.example.sell.demo.repository.UserInfoRepository;
import com.example.sell.demo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    @Transactional
    public USerInfo create(String username, String password) {
     USerInfo uSerInfo =userInfoRepository.findByUsername(username);
     if (uSerInfo==null){
         USerInfo uSerInfo1=new USerInfo();
         uSerInfo1.setUsername(username);
         uSerInfo1.setPassword(password);
         userInfoRepository.save(uSerInfo1);
         return uSerInfo1;
     }else {
         return null;
     }
     }
     @Transactional
    @Override
    public USerInfo create(String openid) {
        USerInfo uSerInfo =userInfoRepository.findByOpenid(openid);
        if (uSerInfo==null){
            uSerInfo.setOpenid(openid);
            uSerInfo.setUsername("liwanlei");
            uSerInfo.setPassword("beijing");
            userInfoRepository.save(uSerInfo);
            return uSerInfo;
        }else {
            return null;
        }
    }
}
