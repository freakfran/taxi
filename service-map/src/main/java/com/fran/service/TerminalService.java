package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface TerminalService {

    public CommonResult add(String name,String desc);

    public CommonResult<List<TerminalResponse>> aroundSearch(String center, Integer radius);
}
