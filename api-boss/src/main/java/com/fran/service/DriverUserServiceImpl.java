package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUser;
import com.fran.remote.ServiceDriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverUserServiceImpl implements DriverUserService{
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    @Override
    public CommonResult addDriver(DriverUser driverUser) {
        return serviceDriverUserClient.addDriver(driverUser);
    }
}
