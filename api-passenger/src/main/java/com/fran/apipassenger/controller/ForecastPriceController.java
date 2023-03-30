package com.fran.apipassenger.controller;

import com.fran.apipassenger.service.ForecastPriceServiceImpl;
import com.fran.dto.CommonResult;
import com.fran.request.ForecastPriceDto;
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

        return forecastPriceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude);
    }
}
