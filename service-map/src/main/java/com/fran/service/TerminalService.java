package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import com.fran.response.TrSearchResponse;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface TerminalService {

    public CommonResult add(String name,String desc);

    public CommonResult<List<TerminalResponse>> aroundSearch(String center, Integer radius);

    public CommonResult<TrSearchResponse> trackSearch(String tid, Long startTime, Long endTime);
}
