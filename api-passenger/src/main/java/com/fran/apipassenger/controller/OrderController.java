package com.fran.apipassenger.controller;


import com.fran.apipassenger.service.OrderServiceImpl;
import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import com.fran.response.TerminalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @PostMapping("/add")
    public CommonResult order(@RequestBody OrderRequest orderRequest){
        return orderService.add(orderRequest);
    }


    @PostMapping("/testDispatch/{orderId}")
    public CommonResult<List<TerminalResponse>> testDispatch(@PathVariable("orderId")Long orderId){
        return orderService.testDispatch(orderId);
    }

    @PostMapping("/cancel")
    public CommonResult cancel(@RequestParam("orderId") Long orderId){
        return orderService.cancel(orderId);
    }
}
