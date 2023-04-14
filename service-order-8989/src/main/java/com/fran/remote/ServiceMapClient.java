package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("service-map")
public interface ServiceMapClient {
    @PostMapping("/terminal/aroundSearch")
    public CommonResult<List<TerminalResponse>> aroundSearch(@RequestParam("center") String center, @RequestParam("radius") Integer radius);
}
