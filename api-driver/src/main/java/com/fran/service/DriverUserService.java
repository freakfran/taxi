package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUser;

public interface DriverUserService {

    public CommonResult addDriver(DriverUser driverUser);

    public CommonResult updateDriver(DriverUser driverUser);
}
