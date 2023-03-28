package com.fran.apipassenger.service;

import com.fran.dto.CommonResult;

public interface VerificationCodeService {
    public CommonResult generateCode(String passengerPhone);
}
