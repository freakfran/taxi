package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.ServiceOrderClient;
import com.fran.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ServiceOrderClient serviceOrderClient;

    @Override
    public CommonResult toPickUpPassenger(OrderRequest orderRequest) {
        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }

    @Override
    public CommonResult arriveDeparture(OrderRequest orderRequest) {
        return serviceOrderClient.arriveDeparture(orderRequest);
    }

    @Override
    public CommonResult pickUpPassenger(OrderRequest orderRequest) {
        return serviceOrderClient.pickUpPassenger(orderRequest);
    }

    @Override
    public CommonResult passengerGetoff(OrderRequest orderRequest) {
        return serviceOrderClient.passengerGetoff(orderRequest);
    }


}
