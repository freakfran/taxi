package com.fran.apipassenger.service;

import com.alibaba.fastjson.JSONObject;
import com.fran.apipassenger.remote.VerificationCodeClient;
import com.fran.dto.CommonResult;
import com.fran.response.NumberCodeResponse;
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

    private String verificationCodePrefix = "passenger-verification-code";

    @Override
    public CommonResult generateCode(String passengerPhone) {
        //调用验证码服务，生成验证码
        System.out.println("调用验证码服务，生成验证码");
        CommonResult<NumberCodeResponse> numberCodeResponse = verificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("numbercode="+numberCode);

        //验证码存入redis key,value,ttl
        String key = verificationCodePrefix + passengerPhone;
        String value = String.valueOf(numberCode);
        stringRedisTemplate.opsForValue().set(key,value,2, TimeUnit.MINUTES);
        System.out.println("验证码存入redis");
        //通过第三方短信服务发送验证码
        return CommonResult.success();
    }
}
