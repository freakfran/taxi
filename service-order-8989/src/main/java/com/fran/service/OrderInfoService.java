package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;

public interface OrderInfoService {

    public CommonResult add(OrderRequest orderRequest);
}
