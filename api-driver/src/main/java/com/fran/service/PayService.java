package com.fran.service;

import com.fran.dto.CommonResult;

public interface PayService {

    public CommonResult pushPayInfo(Long orderId, Double price, Long passengerId);
}
