package com.fran.service;

import com.fran.dto.CommonResult;

public interface ForecastPriceService {
    public CommonResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude,String cityCode,String vehicleType);

    public CommonResult<Double> calculatePrice(Integer distance,Integer duration,String cityCode,String vehicleType);
}
