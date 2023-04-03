package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.TerminalClient;
import com.fran.response.TerminalResponse;
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
}
