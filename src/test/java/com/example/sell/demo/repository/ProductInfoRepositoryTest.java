package com.example.sell.demo.repository;

import com.example.sell.demo.daoobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private  ProductInfoRepository repository;
    @Test
    public  void add(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setCategoryType(0);
        productInfo.setProductName("北京");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        repository.save(productInfo);
    }
}