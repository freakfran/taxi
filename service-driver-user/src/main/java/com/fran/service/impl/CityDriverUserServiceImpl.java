package com.fran.service.impl;

import com.fran.dto.CommonResult;
import com.fran.mapper.DriverUserMapper;
import com.fran.service.CityDriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityDriverUserServiceImpl implements CityDriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    @Override
    public CommonResult<Boolean> hasAvailableDriver(String cityCode) {
        Integer count = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        return count > 0 ? CommonResult.success(true) : CommonResult.success(false);
    }
}
