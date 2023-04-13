package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUser;
import com.fran.response.DriverUserExistsResponse;
import com.fran.response.OrderDriverResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DriverUserService {
    public CommonResult addDriver(DriverUser driverUser);
    public CommonResult updateDriver(DriverUser driverUser);
    public CommonResult<DriverUser> getDriverByPhone(String driverPhone);
    public CommonResult<OrderDriverResponse> getAvailableDriver(Long carId);
}
