package com.fran.apipassenger.service;

import com.alibaba.fastjson.JSONObject;
import com.fran.apipassenger.remote.VerificationCodeClient;
import com.fran.dto.CommonResult;
import com.fran.response.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService{

    @Autowired
    private VerificationCodeClient verificationCodeClient;

    @Override
    public String generateCode(String passengerPhone) {
        //调用验证码服务，生成验证码
        System.out.println("调用验证码服务，生成验证码");
        CommonResult<NumberCodeResponse> numberCodeResponse = verificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("numbercode="+numberCode);
        //验证码存入redis
        System.out.println("验证码存入redis");
        //返回JSON
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("message","success");
        return jsonObject.toJSONString();
    }
}
