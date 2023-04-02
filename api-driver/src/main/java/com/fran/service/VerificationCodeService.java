package com.fran.service;

import com.fran.dto.CommonResult;

public interface VerificationCodeService {

    public CommonResult getVerificationCode(String driverPhone);

    public CommonResult checkCode(String driverPhone,String verificationCode);
}
