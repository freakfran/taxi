package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import com.fran.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/to_pick_up_passenger")
    public CommonResult toPickUpPassenger(@RequestBody OrderRequest orderRequest){
        return orderService.toPickUpPassenger(orderRequest);
    }

    @PostMapping("/arrive_departure")
    public CommonResult arriveDeparture(@RequestBody OrderRequest orderRequest){
        return orderService.arriveDeparture(orderRequest);
    }

    @PostMapping("/pick_up_passenger")
    public CommonResult pickUpPassenger(@RequestBody OrderRequest orderRequest){
        return orderService.pickUpPassenger(orderRequest);
    }

    @PostMapping("/passenger_get_off")
    public CommonResult passengerGetoff(@RequestBody OrderRequest orderRequest){
        return orderService.passengerGetoff(orderRequest);
    }


}
