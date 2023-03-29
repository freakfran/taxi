package com.fran.apipassenger.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fran.constant.CommonStatusEnum;
import com.fran.constant.TokenTypeConstants;
import com.fran.dto.CommonResult;
import com.fran.dto.TokenResult;
import com.fran.response.TokenResponse;
import com.fran.util.JwtUtils;
import com.fran.util.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public CommonResult refreshToken(String refreshTokenSrc) {
        //解析refreshToken
        TokenResult checkToken = JwtUtils.checkToken(refreshTokenSrc);
        if(checkToken==null){
            return CommonResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode()
            ,CommonStatusEnum.TOKEN_ERROR.getMessage());
        }
        String phone = checkToken.getPhone();
        String identity = checkToken.getIdentity();

        //去redis中读取refreshToken
        String refreshKey = RedisKeyUtils.generateTokenKey(phone, identity, TokenTypeConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshKey);

        //校验token
        if(StringUtils.isBlank(refreshTokenRedis)||(!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))){
            return CommonResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode()
                    ,CommonStatusEnum.TOKEN_ERROR.getMessage());
        }

        //重新生成双token
        String accessToken = JwtUtils.generateToken(phone, identity, TokenTypeConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(phone, identity, TokenTypeConstants.ACCESS_TOKEN_TYPE);

        String accessKey = RedisKeyUtils.generateTokenKey(phone,identity,TokenTypeConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessKey,accessToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshKey,refreshToken,31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);
        return CommonResult.success(tokenResponse);
    }
}
