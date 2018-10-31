package com.example.sell.demo.repository;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 12:46
 */

import com.example.sell.demo.daoobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStats);
}
