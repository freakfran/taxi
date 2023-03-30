package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.request.ForecastPriceDto;
import com.fran.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-map")
public interface ServiceMapClient {
    @GetMapping("/direction/driving")
    public CommonResult<DirectionResponse> driving(@RequestBody ForecastPriceDto forecastPriceDto);
}
