package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.AMapClient;
import com.fran.response.DirectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectionServiceImpl implements DirectionService{
    @Autowired
    private AMapClient aMapClient;

    @Override
    public CommonResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        DirectionResponse driving = aMapClient.driving(depLongitude, depLatitude, destLongitude, destLatitude);
        if(driving==null){
            return CommonResult.fail("请求失败");
        }else {
            return CommonResult.success(driving);
        }

    }
}
