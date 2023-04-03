package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.ApiDriverPointRequest;
import com.fran.service.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointServiceImpl pointService;
    @PostMapping("/upload")
    public CommonResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){
        return pointService.upload(apiDriverPointRequest);
    }
}
