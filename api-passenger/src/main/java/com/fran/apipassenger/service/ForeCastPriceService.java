package com.fran.apipassenger.service;

import com.fran.dto.CommonResult;
import com.fran.request.ForecastPriceDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface ForeCastPriceService {

    public CommonResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude);
}
