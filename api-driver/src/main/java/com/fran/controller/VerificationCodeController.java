package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import com.fran.service.VerificationCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
