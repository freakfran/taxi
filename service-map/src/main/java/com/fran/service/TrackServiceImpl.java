package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.TrackClient;
import com.fran.response.TrackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService{
    @Autowired
    private TrackClient trackClient;

    public CommonResult<TrackResponse> add(Long tid){

        return trackClient.add(tid);
    }
}
