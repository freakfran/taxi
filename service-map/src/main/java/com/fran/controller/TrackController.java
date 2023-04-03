package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.response.TrackResponse;
import com.fran.service.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    private TrackServiceImpl trackService;

    @PostMapping("add")
    public CommonResult<TrackResponse> add(Integer tid){
        return trackService.add(tid);
    }
}
