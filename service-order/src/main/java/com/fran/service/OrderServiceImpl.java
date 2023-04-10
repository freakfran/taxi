package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Override
    public CommonResult add(OrderRequest orderRequest) {
        log.info(orderRequest.getAddress());
        return CommonResult.success();
    }
}
