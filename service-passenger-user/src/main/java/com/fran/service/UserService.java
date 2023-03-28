package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.VerificationCodeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {
    public CommonResult loginOrRegister(String passengerPhone);
}
