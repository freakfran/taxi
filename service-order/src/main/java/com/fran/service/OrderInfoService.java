package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderInfoService {

    public CommonResult add(OrderRequest orderRequest);

    public CommonResult toPickUpPassenger(OrderRequest orderRequest);

    public CommonResult arriveDeparture(OrderRequest orderRequest);

    public CommonResult pickUpPassenger(OrderRequest orderRequest);

    public CommonResult passengerGetoff(OrderRequest orderRequest);
}
