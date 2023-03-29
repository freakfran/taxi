package com.fran.apipassenger.controller;

import com.fran.apipassenger.service.TokenServiceImpl;
import com.fran.dto.CommonResult;
import com.fran.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    @Autowired
    private TokenServiceImpl tokenService;

    @PostMapping("/token_refresh")
    public CommonResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken：" + refreshTokenSrc);
        return tokenService.refreshToken(refreshTokenSrc);
    }
}
