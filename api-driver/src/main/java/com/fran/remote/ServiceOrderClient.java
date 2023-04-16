package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-order")
public interface ServiceOrderClient {
    @PostMapping("/order/to_pick_up_passenger")
    public CommonResult toPickUpPassenger(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/arrive_departure")
    public CommonResult arriveDeparture(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/pick_up_passenger")
    public CommonResult pickUpPassenger(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/passenger_get_off")
    public CommonResult passengerGetoff(@RequestBody OrderRequest orderRequest);
}
