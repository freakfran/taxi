package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUser;
import com.fran.service.DriverUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {
    @Autowired
    private DriverUserServiceImpl driverUserService;

    @PostMapping("/driver_user")
    public CommonResult addDriver(@RequestBody DriverUser driverUser){

        return driverUserService.addDriver(driverUser);
    }
}
