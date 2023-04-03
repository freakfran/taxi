package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.PointClient;
import com.fran.request.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PointServiceImpl implements PointService{
    @Autowired
    private PointClient pointClient;


    @Override
    public CommonResult upload(PointRequest pointRequest) {
        return pointClient.upload(pointRequest);
    }
}
