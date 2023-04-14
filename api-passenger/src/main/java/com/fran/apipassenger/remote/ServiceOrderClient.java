package com.fran.apipassenger.remote;

import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import com.fran.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("service-order")
public interface ServiceOrderClient {
    @PostMapping("/order/add")
    public CommonResult add(@RequestBody OrderRequest orderRequest);

    @PostMapping("/order/testDispatch/{orderId}")
    public CommonResult<List<TerminalResponse>> testDispatch(@PathVariable("orderId")Long orderId);
}
