package com.fran.response;

import lombok.Data;

@Data
public class ForecastPriceResponse {
    private double price;
    private String cityCode;
    private String vehicleType;
}
