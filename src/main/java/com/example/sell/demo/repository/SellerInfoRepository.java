package com.example.sell.demo.repository;

import com.example.sell.demo.daoobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo ,String> {
    SellerInfo findByOpenid(String openid);
}
