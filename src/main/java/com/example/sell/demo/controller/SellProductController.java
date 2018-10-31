package com.example.sell.demo.controller;/*
 *
 * @author lileilei
 * Date: 2018/10/26
 * Time: 9:07
 */

import com.example.sell.demo.daoobject.ProductCategory;
import com.example.sell.demo.daoobject.ProductInfo;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.service.CategoryService;
import com.example.sell.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sell/product")
public class SellProductController {
    @Autowired
    private ProductService orderService;
    @Autowired
    private CategoryService categoryService;
    /*
    获取所有的商品
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size" ,defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<ProductInfo> productInfos=orderService.findAll(pageRequest);
        map.put("productInfos",productInfos);
        map.put("curetPage",page);
        return  new ModelAndView("product/list",map);
    }
    //上架
    @GetMapping("/onsale")
    public  ModelAndView onsale(@RequestParam("productId") String productId,
                                Map<String,Object> map){
        try{
            orderService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/product/list");
            return new ModelAndView("common/err",map);
        }
        map.put("url","/sell/product/list");
        return new ModelAndView("common/success",map);

    }
    //下架
    @GetMapping("/offsale")
    public  ModelAndView offsale(@RequestParam("productId") String productId,
                                Map<String,Object> map){
        try{
            orderService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/product/list");
            return new ModelAndView("common/err",map);
        }
        map.put("url","/sell/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/index")
    public  ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                       Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo=orderService.findOne(productId);
            map.put("productInfo",productInfo);
        }
       List<ProductCategory> productCategoryList= categoryService.findAll();
        map.put("categroylist",productCategoryList);
        return new  ModelAndView("product/index",map);
    }
}
