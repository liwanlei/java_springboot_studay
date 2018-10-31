package com.example.sell.demo.repository;/*
 *类目
 * @author lileilei
 * Date: 2018/10/23
 * Time: 9:51
 */

import com.example.sell.demo.daoobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductCategoryRepository extends JpaRepository <ProductCategory,Integer > {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
