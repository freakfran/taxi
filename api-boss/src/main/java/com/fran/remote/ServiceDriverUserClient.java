package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.pojo.DriverCarBindingRelationship;
import com.fran.pojo.DriverUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-driver-user")
@Service
public interface ServiceDriverUserClient {

    @PostMapping("/user")
    public CommonResult addDriver(@RequestBody DriverUser driverUser);

    @PutMapping("/user")
    public CommonResult updateDriver(@RequestBody DriverUser driverUser);

    @PostMapping("/car")
    public CommonResult addCar(@RequestBody Car car);

    @PostMapping("/driver_car_binding_relationship/bind")
    public CommonResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @PostMapping("/driver_car_binding_relationship/unbind")
    public CommonResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
}
