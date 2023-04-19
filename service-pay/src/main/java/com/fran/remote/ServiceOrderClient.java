package com.fran.remote;

import com.fran.dto.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-order")
public interface ServiceOrderClient {
    @PostMapping("/order/pay")
    public CommonResult pay(@RequestParam("orderId")String orderId);
}
