package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.PointRequest;
import com.fran.service.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointServiceImpl pointService;

    @PostMapping("/upload")
    public CommonResult upload(@RequestBody PointRequest pointRequest){
        return pointService.upload(pointRequest);
    }
}
