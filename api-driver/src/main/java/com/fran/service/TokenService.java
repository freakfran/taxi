package com.fran.service;

import com.fran.dto.CommonResult;

public interface TokenService {

    public CommonResult refreshToken(String refreshTokenSrc);
}
