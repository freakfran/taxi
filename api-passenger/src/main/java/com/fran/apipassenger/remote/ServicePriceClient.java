package com.fran.apipassenger.remote;

import com.fran.dto.CommonResult;
import com.fran.request.ForecastPriceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-price")
public interface ServicePriceClient {
    @PostMapping("/forecast_price")
    public CommonResult forecastPrice(@RequestBody ForecastPriceDto forecastPriceDto);
}
