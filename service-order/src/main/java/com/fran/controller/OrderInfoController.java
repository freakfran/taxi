package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import com.fran.response.TerminalResponse;
import com.fran.service.OrderInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoServiceImpl orderService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody OrderRequest orderRequest){
//        String deviceCode = request.getHeader(HeaderConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);
        return orderService.add(orderRequest);
    }

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




    @PostMapping("/testDispatch/{orderId}")
    public CommonResult<List<TerminalResponse>> testDispatch(@PathVariable("orderId")Long orderId){
//        String deviceCode = request.getHeader(HeaderConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);
        return orderService.dispatchRealTimeOrder(orderId);
    }
}
