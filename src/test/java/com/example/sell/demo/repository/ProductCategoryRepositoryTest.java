package com.example.sell.demo.repository;

import com.example.sell.demo.daoobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public  void  addoone(){
//         ProductCategory productCategory1= repository.getOne(1);
//         assertTrue(productCategory1.equals(ProductCategory.class));
        ProductCategory productCategory1=new  ProductCategory();
        productCategory1.setCategoryName("我的11");
        productCategory1.setCategoryType(2);
        repository.save(productCategory1);
    }
}