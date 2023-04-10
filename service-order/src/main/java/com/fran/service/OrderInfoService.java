package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderInfoService {

    public CommonResult add(OrderRequest orderRequest);
}
