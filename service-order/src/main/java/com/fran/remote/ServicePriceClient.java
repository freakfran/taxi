package com.fran.remote;

import com.fran.dto.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-price")
public interface ServicePriceClient {
    @GetMapping("/price_rule/isLatest")
    public CommonResult<Boolean> isLatest(@RequestParam("fareType") String fareType, @RequestParam("fareVersion") Integer fareVersion);
}
