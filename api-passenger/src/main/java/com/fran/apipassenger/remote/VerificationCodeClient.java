package com.fran.apipassenger.remote;

import com.fran.dto.CommonResult;
import com.fran.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-verificationcode")
public interface VerificationCodeClient {

    @GetMapping("/numberCode/{size}")
    public CommonResult<NumberCodeResponse> getNumberCode(@PathVariable("size")int size);
}
