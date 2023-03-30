package com.fran.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PriceRule {
    private String cityCode;
    private String vehicleType;
    private Double startFare;
    private Integer startMile;
    private Double unitPricePerMile;
    private Double unitPricePerMinute;
}
