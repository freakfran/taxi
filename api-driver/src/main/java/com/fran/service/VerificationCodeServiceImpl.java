package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.ServiceDriverUserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService{
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    @Override
    public CommonResult getVerificationCode(String driverPhone) {
        log.info(driverPhone);
        //查询service-driver-user该司机是否存在


        //获取验证码



        //发送验证码


        //存入redis
        return CommonResult.success();
    }
}
