package com.fran.apipassenger.service;

import com.fran.apipassenger.remote.PassengerUserClient;
import com.fran.dto.CommonResult;
import com.fran.dto.TokenResult;
import com.fran.pojo.PassengerUser;
import com.fran.request.VerificationCodeDTO;
import com.fran.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private PassengerUserClient passengerUserClient;
    @Override
    public CommonResult getUserByAccessToken(String accessToken) {
        //解析accesstoken拿到手机号
        TokenResult tokenResult = JwtUtils.parseToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info(phone);

        //调用服务验证手机号返回信息
        CommonResult<PassengerUser> userByPhone = passengerUserClient.getUserByPhone(phone);

        return CommonResult.success(userByPhone.getData());
    }
}
