package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.request.ForecastPriceDto;
import com.fran.service.ForecastPriceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForecastPriceController {
    @Autowired
    private ForecastPriceServiceImpl forecastPriceService;


    @PostMapping("/forecast_price")
    public CommonResult forecastPrice(@RequestBody ForecastPriceDto forecastPriceDto){

        String depLongitude = forecastPriceDto.getDepLongitude();
        String depLatitude = forecastPriceDto.getDepLatitude();
        String destLongitude = forecastPriceDto.getDestLongitude();
        String destLatitude = forecastPriceDto.getDestLatitude();
        String cityCode = forecastPriceDto.getCityCode();
        String vehicleType = forecastPriceDto.getVehicleType();

        return forecastPriceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude,cityCode,vehicleType);
    }
}
