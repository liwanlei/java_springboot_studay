package com.example.sell.demo.handel;/*
 *
 * @author lileilei
 * Date: 2018/10/26
 * Time: 11:32
 */

import com.example.sell.demo.VO.ResultVO;
import com.example.sell.demo.exception.SellAeeException;
import com.example.sell.demo.exception.SellException;
import com.example.sell.demo.untils.ResultVOUntils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptonHandel {
//    拦截登录一次
//    @ExceptionHandler(value = SellAeeException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ModelAndView hadelLoginException(){
//        return  new ModelAndView("redirect:login");
//    }
    @ExceptionHandler(value =SellException.class)
    @ResponseBody
    public ResultVO haselsell(SellException e){

        return ResultVOUntils.error(e.getCode(),e.getMessage());
    }
}
