package com.fran.service;

import com.alibaba.fastjson.JSONObject;
import com.fran.constant.IdentityConstants;
import com.fran.dto.CommonResult;
import com.fran.remote.ServiceSSEPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/pay")
public class PayServiceImpl implements PayService{
    @Autowired
    private ServiceSSEPushClient serviceSSEPushClient;
    @Override
    public CommonResult pushPayInfo(Long orderId, Double price, Long passengerId) {
        //封装消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("price",price);
        //推送消息
        serviceSSEPushClient.push(passengerId.toString(), IdentityConstants.IDENTITY_PASSENGER,jsonObject.toJSONString());
        //返回
        return CommonResult.success();
    }
}
