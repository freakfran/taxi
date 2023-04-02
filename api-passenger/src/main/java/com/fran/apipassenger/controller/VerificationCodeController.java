package com.fran.apipassenger.controller;

import com.fran.apipassenger.service.VerificationCodeServiceImpl;
import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeServiceImpl verificationCodeService;



    @GetMapping("/verification_code")
    public CommonResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号" + passengerPhone);
        return verificationCodeService.generateCode(passengerPhone);
    }

    @PostMapping("/verification_code_check")
    public CommonResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        System.out.println("乘客手机号："+passengerPhone);
        System.out.println("验证码"+verificationCode);

        return verificationCodeService.checkCode(passengerPhone,verificationCode);
    }
}
