package com.example.sell.demo.daoobject;/*
 *类目
 * @author lileilei
 * Date: 2018/10/23
 * Time: 9:44
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@DynamicUpdate
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue
    private  Integer categoryId;
    private  String categoryName;
    private  Integer categoryType;
    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
    public ProductCategory() {
    }
}
