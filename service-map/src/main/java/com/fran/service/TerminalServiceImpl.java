package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.TerminalClient;
import com.fran.response.TerminalResponse;
import com.fran.response.TrSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalServiceImpl implements TerminalService{
    @Autowired
    private TerminalClient terminalClient;

    @Override
    public CommonResult<TerminalResponse> add(String name,String desc) {
        return terminalClient.add(name,desc);
    }

    @Override
    public CommonResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {
        return terminalClient.aroundSearch(center,radius);
    }

    @Override
    public CommonResult<TrSearchResponse> trackSearch(String tid, Long startTime, Long endTime) {
        return terminalClient.trackSearch(tid,startTime,endTime);
    }
}
