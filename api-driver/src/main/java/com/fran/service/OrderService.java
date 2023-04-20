package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;


public interface OrderService {
    public CommonResult toPickUpPassenger(OrderRequest orderRequest);

    public CommonResult arriveDeparture(OrderRequest orderRequest);

    public CommonResult pickUpPassenger(OrderRequest orderRequest);

    public CommonResult passengerGetoff(OrderRequest orderRequest);

    public CommonResult cancel(Long orderId);


}
