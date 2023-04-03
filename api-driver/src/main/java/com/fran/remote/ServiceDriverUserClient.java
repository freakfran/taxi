package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.pojo.DriverUser;
import com.fran.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-driver-user")
@Service
public interface ServiceDriverUserClient {

    @PostMapping("/user")
    public CommonResult addDriver(@RequestBody DriverUser driverUser);

    @PutMapping("/user")
    public CommonResult updateDriver(@RequestBody DriverUser driverUser);

    @GetMapping("/car")
    public CommonResult<Car> getCarById(@RequestParam("carId") Long carId);

    @GetMapping("/check_driver/{driverPhone}")
    public CommonResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone")String driverPhone);
}
