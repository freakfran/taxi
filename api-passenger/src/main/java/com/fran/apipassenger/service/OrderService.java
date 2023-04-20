package com.fran.apipassenger.service;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;

public interface OrderService {

    public CommonResult add(OrderRequest orderRequest);

    public CommonResult cancel(Long orderId);
}
