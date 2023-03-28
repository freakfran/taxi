package com.fran.serviceverificationcode.controller;

import com.alibaba.fastjson.JSONObject;
import com.fran.dto.CommonResult;
import com.fran.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public CommonResult numberCode(@PathVariable("size")Integer size){
        System.out.println("size="+size);
        //生成随机验证码 Math.random():[0,1)之间随机数，乘9让他首位不为0，加1防止全0情况
        double random = (Math.random()*9+1)*Math.pow(10,size-1);
        int numberCode = (int) random;

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(numberCode);
        return CommonResult.success(response);
    }
}
