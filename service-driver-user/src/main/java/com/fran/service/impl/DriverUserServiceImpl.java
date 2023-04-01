package com.fran.service.impl;

import com.fran.dto.CommonResult;
import com.fran.mapper.DriverUserMapper;
import com.fran.pojo.DriverUser;
import com.fran.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DriverUserServiceImpl implements DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;

    public CommonResult addDriver(DriverUser driverUser){
        driverUser.setGmtCreate(LocalDateTime.now());
        driverUser.setGmtModified(LocalDateTime.now());
        driverUserMapper.insert(driverUser);

        return CommonResult.success();
    }

    @Override
    public CommonResult updateDriver(DriverUser driverUser) {
        driverUser.setGmtModified(LocalDateTime.now());
        int update = driverUserMapper.updateById(driverUser);
        return CommonResult.success();
    }

}
