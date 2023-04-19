package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.service.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Autowired
    private PayServiceImpl payService;
    @PostMapping("/push_pay_info")
    public CommonResult pushPayInfo(@RequestParam Long orderId, @RequestParam Double price,@RequestParam Long passengerId){
        return payService.pushPayInfo(orderId,price,passengerId);
    }
}
