package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import com.fran.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user")
    public CommonResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public CommonResult getUser(@PathVariable("phone") String phone){
        return userService.getUserByPhone(phone);
    }
}
