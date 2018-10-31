package com.example.sell.demo.untils;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 14:26
 */

import com.example.sell.demo.VO.ResultVO;

public class ResultVOUntils {
    public  static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setData(object);
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }
    public  static ResultVO success(){
      return success(null);
    }
    public  static  ResultVO error(Integer code ,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
