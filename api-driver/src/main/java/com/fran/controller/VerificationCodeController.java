package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import com.fran.service.VerificationCodeServiceImpl;
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
    public CommonResult getVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        return verificationCodeService.getVerificationCode(verificationCodeDTO.getDriverPhone());
    }

    @PostMapping("/verification_code_check")
    public CommonResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String driverPhone = verificationCodeDTO.getDriverPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        System.out.println("司机手机号："+driverPhone);
        System.out.println("验证码"+verificationCode);

        return verificationCodeService.checkCode(driverPhone,verificationCode);
    }
}
