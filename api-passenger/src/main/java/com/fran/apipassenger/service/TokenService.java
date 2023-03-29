package com.fran.apipassenger.service;

import com.fran.dto.CommonResult;

public interface TokenService {

    public CommonResult refreshToken(String refreshTokenSrc);
}
