package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.ServiceOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService{
    @Autowired
    private ServiceOrderClient serviceOrderClient;

    @Override
    public void pay(String orderId) {
        serviceOrderClient.pay(orderId);
    }
}
