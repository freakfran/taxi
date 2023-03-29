package com.fran.apipassenger.controller;

import com.fran.apipassenger.service.UserServiceImpl;
import com.fran.dto.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/user")
    public CommonResult getUser(HttpServletRequest request){
        //从header中获取token
        String authorization = request.getHeader("Authorization");
        return userService.getUserByAccessToken(authorization);
    }
}
