package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.request.PointRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-map")
public interface ServiceMapClient {

    @PostMapping("/point/upload")
    public CommonResult upload(@RequestBody PointRequest pointRequest);
}
