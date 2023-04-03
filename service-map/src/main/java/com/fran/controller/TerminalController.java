package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import com.fran.service.TerminalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Autowired
    private TerminalServiceImpl terminalService;

    @PostMapping("/add")
    public CommonResult<TerminalResponse> add(String name,String desc){
        return terminalService.add(name,desc);
    }

    @PostMapping("/aroundSearch")
    public CommonResult<List<TerminalResponse>> aroundSearch(String center, Integer radius){
        return terminalService.aroundSearch(center,radius);
    }
}
