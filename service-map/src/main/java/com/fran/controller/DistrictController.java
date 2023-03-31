package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.service.DistrictServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DistrictController {

    @Autowired
    private DistrictServiceImpl districtService;


    @GetMapping("/initDistrict")
    public CommonResult initDicDistrict(String keywords){
        return districtService.initDicDistrict(keywords);
    }
}
