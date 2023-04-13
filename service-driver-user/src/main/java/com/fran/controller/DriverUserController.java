package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.mapper.DriverUserMapper;
import com.fran.pojo.DriverUser;
import com.fran.response.DriverUserExistsResponse;
import com.fran.response.OrderDriverResponse;
import com.fran.service.impl.CityDriverUserServiceImpl;
import com.fran.service.impl.DriverUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class DriverUserController {
    @Autowired
    private DriverUserServiceImpl driverUserService;


    //新增
    @PostMapping("/user")
    public CommonResult addDriver(@RequestBody DriverUser driverUser){
        return driverUserService.addDriver(driverUser);
    }

    //修改
    @PutMapping("/user")
    public CommonResult updateDriver(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriver(driverUser);
    }

    @GetMapping("/check_driver/{driverPhone}")
    public CommonResult<DriverUserExistsResponse> getDriver(@PathVariable("driverPhone")String driverPhone){
        CommonResult<DriverUser> driverByPhone = driverUserService.getDriverByPhone(driverPhone);
        DriverUser data = driverByPhone.getData();
        int isExists = 1;
        if(data==null){
            isExists = 0;
        }
        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();
        driverUserExistsResponse.setDriverPhone(driverPhone);
        driverUserExistsResponse.setIsExists(isExists);
        return CommonResult.success(driverUserExistsResponse);
    }

    @GetMapping("/get_available_driver/{carId}")
    public CommonResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId")Long carId){
        return driverUserService.getAvailableDriver(carId);
    }

}
