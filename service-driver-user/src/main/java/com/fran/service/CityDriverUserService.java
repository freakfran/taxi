package com.fran.service;

import com.fran.dto.CommonResult;

public interface CityDriverUserService {
    public CommonResult<Boolean> hasAvailableDriver(String cityCode);
}
