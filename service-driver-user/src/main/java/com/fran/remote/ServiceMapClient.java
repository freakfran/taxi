package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-map")
public interface ServiceMapClient {
    @PostMapping("/terminal/add")
    public CommonResult<TerminalResponse> add(@RequestParam("name") String name);
}
