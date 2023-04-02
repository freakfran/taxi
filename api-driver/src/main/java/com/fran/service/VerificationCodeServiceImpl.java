package com.fran.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.fran.constant.CommonStatusEnum;
import com.fran.constant.IdentityConstants;
import com.fran.constant.TokenTypeConstants;
import com.fran.dto.CommonResult;
import com.fran.remote.ServiceDriverUserClient;
import com.fran.remote.ServiceVerificationCodeClient;
import com.fran.request.VerificationCodeDTO;
import com.fran.response.DriverUserExistsResponse;
import com.fran.response.NumberCodeResponse;
import com.fran.response.TokenResponse;
import com.fran.util.JwtUtils;
import com.fran.util.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService{
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public CommonResult getVerificationCode(String driverPhone) {
        log.info(driverPhone);
        //查询service-driver-user该司机是否存在
        CommonResult<DriverUserExistsResponse> driver = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse check = driver.getData();
        if(check.getIsExists()==0){
            return CommonResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(),CommonStatusEnum.DRIVER_NOT_EXISTS.getMessage());
        }
        //获取验证码
        CommonResult<NumberCodeResponse> numberCodeResult = serviceVerificationCodeClient.numberCode(6);
        int numberCode = numberCodeResult.getData().getNumberCode();
        log.info("numbercode of driver:"+numberCode);
        //发送验证码,第三方


        //存入redis
        String key = RedisKeyUtils.generateKeyByPhone(driverPhone, IdentityConstants.IDENTITY_DRIVER);
        stringRedisTemplate.opsForValue().set(key,String.valueOf(numberCode),2, TimeUnit.MINUTES);



        return CommonResult.success();
    }


    @Override
    public CommonResult checkCode(String driverPhone, String verificationCode) {
        //根据手机号，从redis读取验证码
        String key = RedisKeyUtils.generateKeyByPhone(driverPhone,IdentityConstants.IDENTITY_DRIVER);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        //校验验证码
        if(StringUtils.isBlank(codeRedis)||!(verificationCode.trim().equals(codeRedis.trim()))){
            return CommonResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode()
                    ,CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }

        //生成token并存入
        String accessToken = JwtUtils.generateToken(driverPhone
                , IdentityConstants.IDENTITY_DRIVER, TokenTypeConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(driverPhone
                , IdentityConstants.IDENTITY_DRIVER,TokenTypeConstants.REFRESH_TOKEN_TYPE);



        key = RedisKeyUtils.generateTokenKey(driverPhone,IdentityConstants.IDENTITY_DRIVER
                ,TokenTypeConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(key,accessToken,30,TimeUnit.DAYS);
        //refreshtoken要比accesstoken晚过期
        key = RedisKeyUtils.generateTokenKey(driverPhone,IdentityConstants.IDENTITY_DRIVER
                ,TokenTypeConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(key,accessToken,31,TimeUnit.DAYS);

        //创建响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return CommonResult.success(tokenResponse);
    }
}
