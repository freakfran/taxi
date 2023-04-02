package com.fran.service;

import com.fran.constant.CommonStatusEnum;
import com.fran.constant.IdentityConstants;
import com.fran.dto.CommonResult;
import com.fran.remote.ServiceDriverUserClient;
import com.fran.remote.ServiceVerificationCodeClient;
import com.fran.response.DriverUserExistsResponse;
import com.fran.response.NumberCodeResponse;
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
}
