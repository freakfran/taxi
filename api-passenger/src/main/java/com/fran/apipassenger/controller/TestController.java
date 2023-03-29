package com.fran.apipassenger.controller;

import com.fran.dto.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/noAuth")
    public String noAuth(){
        return "test";
    }

    @GetMapping("/Auth")
    public CommonResult auth(){
        return CommonResult.success();
    }
}
