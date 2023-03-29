package com.fran.apipassenger.remote;

import com.fran.dto.CommonResult;
import com.fran.pojo.PassengerUser;
import com.fran.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-passenger-user")
public interface PassengerUserClient {
    @PostMapping("/user")
    public CommonResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    //在feign中如果使用RequestBody接收参数会强制转换成get方法
    @GetMapping("/user/{phone}")
    public CommonResult<PassengerUser> getUserByPhone(@PathVariable("phone") String phone);
}
