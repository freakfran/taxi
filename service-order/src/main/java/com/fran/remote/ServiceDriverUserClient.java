package com.fran.remote;

import com.fran.dto.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @GetMapping("/city_driver/hasAvailableDriver")
    public CommonResult<Boolean> hasAvailableDriver(@RequestParam("cityCode") String cityCode);
}
