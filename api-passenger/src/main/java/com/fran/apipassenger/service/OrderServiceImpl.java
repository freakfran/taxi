package com.fran.apipassenger.service;

import com.fran.apipassenger.remote.ServiceOrderClient;
import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ServiceOrderClient serviceOrderClient;
    @Override
    public CommonResult add(OrderRequest orderRequest) {
        return serviceOrderClient.add(orderRequest);
    }
}