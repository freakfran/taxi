package com.fran.response;

import lombok.Data;

@Data
public class OrderDriverResponse {
    private Long carId;
    private Long driverId;
    private String driverPhone;
}
