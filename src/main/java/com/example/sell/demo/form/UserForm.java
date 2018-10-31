package com.example.sell.demo.form;/*
 *
 * @author lileilei
 * Date: 2018/10/31
 * Time: 9:28
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserForm {
    @NotEmpty(message = "姓名必填")
    private String username;
    @NotEmpty(message = "密码必填")
    private  String password;
}
