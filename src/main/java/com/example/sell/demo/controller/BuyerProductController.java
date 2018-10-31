package com.example.sell.demo.controller;/*
 *买家商品
 * @author lileilei
 * Date: 2018/10/23
 * Time: 13:18
 */
import com.example.sell.demo.VO.ProductInfoVO;
import com.example.sell.demo.VO.ProductVO;
import com.example.sell.demo.VO.ResultVO;
import com.example.sell.demo.daoobject.ProductCategory;
import com.example.sell.demo.daoobject.ProductInfo;
import com.example.sell.demo.service.CategoryService;
import com.example.sell.demo.service.ProductService;
import com.example.sell.demo.untils.ResultVOUntils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList=productService.findUpAll();
        //2.查询类目(一次性查询)
        List<Integer> categoryTypeList=new ArrayList<>();
        for(ProductInfo productInfo :productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());

        }
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼接
        List<ProductVO> productVOList=new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productInfoVO=new ProductVO();
            productInfoVO.setCategoryname(productCategory.getCategoryName());
            productInfoVO.setCategorytype(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO1=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO1);
                    productInfoVOList.add(productInfoVO1);
                }
            }
            productInfoVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productInfoVO);
        }
        return ResultVOUntils.success( productVOList);
    }
}
