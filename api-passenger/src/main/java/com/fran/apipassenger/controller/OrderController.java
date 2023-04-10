package com.fran.apipassenger.controller;


import com.fran.apipassenger.service.OrderServiceImpl;
import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @PostMapping("/add")
    public CommonResult order(@RequestBody OrderRequest orderRequest){
        return orderService.add(orderRequest);
    }
}
