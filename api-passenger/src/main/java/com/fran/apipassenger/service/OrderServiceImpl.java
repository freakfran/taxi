package com.fran.apipassenger.service;

import com.fran.apipassenger.remote.ServiceOrderClient;
import com.fran.constant.IdentityConstants;
import com.fran.dto.CommonResult;
import com.fran.request.OrderRequest;
import com.fran.response.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ServiceOrderClient serviceOrderClient;
    @Override
    public CommonResult add(OrderRequest orderRequest) {
        return serviceOrderClient.add(orderRequest);
    }

    @Override
    public CommonResult cancel(Long orderId) {
        return serviceOrderClient.cancel(orderId, IdentityConstants.IDENTITY_PASSENGER);
    }

    public CommonResult<List<TerminalResponse>> testDispatch(Long orderId){
        return serviceOrderClient.testDispatch(orderId);
    }
}