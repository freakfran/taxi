package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.response.DirectionResponse;
import org.springframework.stereotype.Service;

@Service
public class DirectionServiceImpl implements DirectionService{
    @Override
    public CommonResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {


        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(132);
        directionResponse.setDuration(5);
        return CommonResult.success(directionResponse);
    }
}
