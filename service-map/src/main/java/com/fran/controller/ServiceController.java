package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.service.ServiceFromMapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceFromMapServiceImpl serviceFromMapService;

    @PostMapping("/add")
    public CommonResult addService(String name){

        return serviceFromMapService.add(name);
    }
}
