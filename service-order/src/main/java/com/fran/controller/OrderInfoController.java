package com.fran.controller;

import com.fran.constant.HeaderConstants;
import com.fran.dto.CommonResult;
import com.fran.pojo.OrderInfo;
import com.fran.request.OrderRequest;
import com.fran.response.TerminalResponse;
import com.fran.service.OrderInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/dispatch")
    public CommonResult<List<TerminalResponse>> testDispatch(@RequestBody OrderInfo orderInfo){
//        String deviceCode = request.getHeader(HeaderConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);
        return orderService.dispatchRealTimeOrder(orderInfo);
    }

    @PostMapping("/testDispatch/{orderId}")
    public CommonResult<List<TerminalResponse>> testDispatch(@PathVariable("orderId")Long orderId){
//        String deviceCode = request.getHeader(HeaderConstants.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);
        return orderService.testDispatchRealTimeOrder(orderId);
    }
}
