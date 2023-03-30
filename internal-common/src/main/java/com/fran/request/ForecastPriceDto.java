package com.fran.request;

import lombok.Data;

@Data
public class ForecastPriceDto {
    private String depLongitude;
    private String depLatitude;
    private String destLatitude;
    private String destLongitude;
}
