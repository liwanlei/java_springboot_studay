package com.example.sell.demo.repository;

import com.example.sell.demo.daoobject.USerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<USerInfo,Integer> {
    USerInfo findByUsername(String username);
    USerInfo findByOpenid(String openid);
}
