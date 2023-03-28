package com.fran.apipassenger.remote;

import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-passenger-user")
public interface PassengerUserClient {
    @PostMapping("/user")
    public CommonResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
