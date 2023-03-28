package com.fran.apipassenger.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fran.apipassenger.remote.PassengerUserClient;
import com.fran.apipassenger.remote.VerificationCodeClient;
import com.fran.constant.CommonStatusEnum;
import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import com.fran.response.NumberCodeResponse;
import com.fran.response.TokenResponse;
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
    private String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }
    @Override
    public CommonResult generateCode(String passengerPhone) {
        //调用验证码服务，生成验证码
        System.out.println("调用验证码服务，生成验证码");
        CommonResult<NumberCodeResponse> numberCodeResponse = verificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("numbercode="+numberCode);

        //验证码存入redis key,value,ttl
        String key = generateKeyByPhone(passengerPhone);
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
        String key = generateKeyByPhone(passengerPhone);
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

        //创建响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token");
        return CommonResult.success(tokenResponse);
    }
}
