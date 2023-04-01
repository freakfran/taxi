package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUser;
import com.fran.service.DriverUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DriverUserController {
    @Autowired
    private DriverUserServiceImpl driverUserService;

    @PostMapping("/user")
    public CommonResult addDriver(@RequestBody DriverUser driverUser){
        return driverUserService.addDriver(driverUser);
    }

    @PutMapping("/user")
    public CommonResult updateDriver(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriver(driverUser);
    }
}
