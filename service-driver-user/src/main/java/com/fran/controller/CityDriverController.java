package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.service.impl.CityDriverUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city_driver")
public class CityDriverController {
    @Autowired
    private CityDriverUserServiceImpl cityDriverUserService;

    @GetMapping("/hasAvailableDriver")
    public CommonResult<Boolean> hasAvailableDriver(String cityCode){
        return cityDriverUserService.hasAvailableDriver(cityCode);
    }
}
