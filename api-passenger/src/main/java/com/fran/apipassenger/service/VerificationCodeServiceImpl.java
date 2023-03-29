package com.fran.apipassenger.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fran.apipassenger.remote.PassengerUserClient;
import com.fran.apipassenger.remote.VerificationCodeClient;
import com.fran.constant.CommonStatusEnum;
import com.fran.constant.IdentityConstants;
import com.fran.constant.TokenTypeConstants;
import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import com.fran.response.NumberCodeResponse;
import com.fran.response.TokenResponse;
import com.fran.util.JwtUtils;
import com.fran.util.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService{

    @Autowired
    private VerificationCodeClient verificationCodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public CommonResult generateCode(String passengerPhone) {
        //调用验证码服务，生成验证码
        System.out.println("调用验证码服务，生成验证码");
        CommonResult<NumberCodeResponse> numberCodeResponse = verificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("numbercode="+numberCode);

        //验证码存入redis key,value,ttl
        String key = RedisKeyUtils.generateKeyByPhone(passengerPhone);
        String value = String.valueOf(numberCode);
        stringRedisTemplate.opsForValue().set(key,value,2, TimeUnit.MINUTES);
        System.out.println("验证码存入redis");
        //通过第三方短信服务发送验证码
        return CommonResult.success();
    }

    @Autowired
    private PassengerUserClient passengerUserClient;
    @Override
    public CommonResult checkCode(String passengerPhone, String verificationCode) {
        //根据手机号，从redis读取验证码
        String key = RedisKeyUtils.generateKeyByPhone(passengerPhone);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        //校验验证码
        if(StringUtils.isBlank(codeRedis)||!(verificationCode.trim().equals(codeRedis.trim()))){
            return CommonResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode()
                    ,CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }
        //判断是否有用户，如果没有，则创建,调用远程服务
        VerificationCodeDTO dto = new VerificationCodeDTO();
        dto.setPassengerPhone(passengerPhone);
        passengerUserClient.loginOrRegister(dto);
        //生成token并存入
        String accessToken = JwtUtils.generateToken(passengerPhone
                , IdentityConstants.IDENTITY_PASSENGER, TokenTypeConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(passengerPhone
                , IdentityConstants.IDENTITY_PASSENGER,TokenTypeConstants.REFRESH_TOKEN_TYPE);



        key = RedisKeyUtils.generateTokenKey(passengerPhone,IdentityConstants.IDENTITY_PASSENGER
                ,TokenTypeConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(key,accessToken,30,TimeUnit.DAYS);
        //refreshtoken要比accesstoken晚过期
        key = RedisKeyUtils.generateTokenKey(passengerPhone,IdentityConstants.IDENTITY_PASSENGER
                ,TokenTypeConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(key,accessToken,31,TimeUnit.DAYS);

        //创建响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return CommonResult.success(tokenResponse);
    }
}
