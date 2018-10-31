package com.example.sell.demo.service.impl;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 12:58
 */

import com.example.sell.demo.daoobject.ProductInfo;
import com.example.sell.demo.dto.CartDTO;
import com.example.sell.demo.enmus.ProductStatysEmuns;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.repository.ProductInfoRepository;
import com.example.sell.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl  implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        try {
            return repository.getOne(productId);
        }catch (RuntimeException e){
            throw new SellException(ResultEmuns.PRODUCT_NOT_EXQIT);
        }
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatysEmuns.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }


    /*
    加库存
     */
    @Override
    @Transactional
    public void increateStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=repository.findById(cartDTO.getProductId()).get();
            if(productInfo==null){
                throw  new SellException(ResultEmuns.PRODUCT_NOT_EXQIT);
            }
            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    /*
    减库存
     */
    @Override
    @Transactional
    public void decreateStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=repository.findById(cartDTO.getProductId()).get();
            if( productInfo == null){
                throw  new SellException(ResultEmuns.PRODUCT_NOT_EXQIT);
            }
            Integer result= productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new  SellException(ResultEmuns.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
    /*
    上架
     */
    @Override
    public ProductInfo onSale(String productid) {
        ProductInfo productInfo=repository.findById(productid).get();
        if(productInfo==null){
            throw  new  SellException(ResultEmuns.PRODUCT_NOT_EXQIT);
        }
        if(productInfo.getproduct()==ProductStatysEmuns.UP){
            throw  new  SellException(ResultEmuns.PRODUCT_STATUS);
        }
        productInfo.setProductStatus(ProductStatysEmuns.UP.getCode());
        return repository.save(productInfo);
    }
/*
下架
 */
    @Override
    public ProductInfo offSale(String productid) {
        ProductInfo productInfo=repository.findById(productid).get();
        if(productInfo==null){
            throw  new  SellException(ResultEmuns.PRODUCT_NOT_EXQIT);
        }
        if(productInfo.getproduct()==ProductStatysEmuns.DOWN){
            throw  new  SellException(ResultEmuns.PRODUCT_STATUS);
        }
        productInfo.setProductStatus(ProductStatysEmuns.DOWN.getCode());

        return repository.save(productInfo);
    }
}
