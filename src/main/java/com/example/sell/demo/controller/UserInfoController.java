package com.example.sell.demo.controller;/*
 *
 * @author lileilei
 * Date: 2018/10/31
 * Time: 9:23
 */

import com.example.sell.demo.VO.ResultVO;
import com.example.sell.demo.converter.UserinfoFrom2USerInfo;
import com.example.sell.demo.daoobject.USerInfo;
import com.example.sell.demo.enmus.ResultEmuns;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.form.UserForm;
import com.example.sell.demo.service.UserInfoService;
import com.example.sell.demo.untils.MD5Until;
import com.example.sell.demo.untils.ResultVOUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.From;
import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("/create")
    public ResultVO create(@Valid UserForm userForm,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultEmuns.PARM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        USerInfo uSerInfo= UserinfoFrom2USerInfo.convert(userForm);
        String Enpassword=null;
        try {
            Enpassword = MD5Until.md5(uSerInfo.getPassword());
        }catch (Exception e){
            throw  new  SellException(ResultEmuns.USER_CREAT_ERROR);
        }
        USerInfo uSerInfo1=userInfoService.create(uSerInfo.getUsername(),Enpassword);
        if(uSerInfo1==null){
            return  ResultVOUntils.error(ResultEmuns.USER_IS_EXSITS.getCode(),ResultEmuns.USER_IS_EXSITS.getMessage());
        }
       return ResultVOUntils.success(ResultEmuns.USER_CREAT_SUCCESS.getMessage());
    }
}
