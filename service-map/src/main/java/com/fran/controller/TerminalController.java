package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import com.fran.service.TerminalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Autowired
    private TerminalServiceImpl terminalService;

    @PostMapping("/add")
    public CommonResult<TerminalResponse> add(String name){

        return terminalService.add(name);
    }
}
