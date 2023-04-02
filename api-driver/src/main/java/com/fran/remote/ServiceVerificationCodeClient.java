package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {
    @GetMapping("/numberCode/{size}")
    public CommonResult<NumberCodeResponse> numberCode(@PathVariable("size")Integer size);
}
