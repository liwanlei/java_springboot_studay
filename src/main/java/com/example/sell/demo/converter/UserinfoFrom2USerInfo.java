package com.example.sell.demo.converter;/*
 *
 * @author lileilei
 * Date: 2018/10/31
 * Time: 9:40
 */

import com.example.sell.demo.daoobject.OrderMaster;
import com.example.sell.demo.daoobject.USerInfo;
import com.example.sell.demo.dto.OrderDto;
import com.example.sell.demo.form.UserForm;
import org.springframework.beans.BeanUtils;

public class UserinfoFrom2USerInfo {
    public  static USerInfo convert(UserForm userForm){
        USerInfo uSerInfo=new USerInfo();
        BeanUtils.copyProperties(userForm,uSerInfo);
        return  uSerInfo;
    }
}
