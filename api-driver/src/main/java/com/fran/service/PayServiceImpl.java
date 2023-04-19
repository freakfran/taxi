package com.fran.service;

import com.fran.dto.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/pay")
public class PayServiceImpl implements PayService{
    @Override
    public CommonResult pushPayInfo(Long orderId, Double price, Long passengerId) {
        //封装消息

        //推送消息

        //返回
        return null;
    }
}
