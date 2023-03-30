package com.fran.service;

import com.fran.dto.CommonResult;

public interface DirectionService {
    public CommonResult driving(String depLongitude,String depLatitude,String destLongitude,String destLatitude);
}
