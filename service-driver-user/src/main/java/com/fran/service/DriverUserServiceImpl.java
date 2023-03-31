package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.mapper.DriverUserMapper;
import com.fran.pojo.DriverUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DriverUserServiceImpl implements DriverUserService{
    @Autowired
    private DriverUserMapper driverUserMapper;

    public CommonResult addDriver(DriverUser driverUser){
        driverUser.setGmtCreate(LocalDateTime.now());
        driverUser.setGmtModified(LocalDateTime.now());
        driverUserMapper.insert(driverUser);

        return CommonResult.success();
    }

}
