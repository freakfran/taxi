package com.fran.apipassenger.remote;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-order")
public interface ServiceOrderClient {
    @PostMapping("/order/add")
    public CommonResult add(@RequestBody OrderRequest orderRequest);
}
