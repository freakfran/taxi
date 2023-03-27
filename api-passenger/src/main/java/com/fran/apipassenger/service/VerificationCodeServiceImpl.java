package com.fran.apipassenger.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService{
    @Override
    public String generateCode(String passengerPhone) {
        //调用验证码服务，生成验证码
        System.out.println("调用验证码服务，生成验证码");
        String code = "111";
        //验证码存入redis
        System.out.println("验证码存入redis");
        //返回JSON
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("message","success");
        return jsonObject.toJSONString();
    }
}
