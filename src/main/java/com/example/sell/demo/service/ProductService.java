package com.example.sell.demo.service;

import com.example.sell.demo.daoobject.ProductInfo;
import com.example.sell.demo.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    //加库存
    void  increateStock(List<CartDTO> cartDTOList);
    //减库存
    void  decreateStock(List<CartDTO> cartDTOList);
    //上架
    ProductInfo onSale(String productid);
    //下架
    ProductInfo offSale(String productid);
}
