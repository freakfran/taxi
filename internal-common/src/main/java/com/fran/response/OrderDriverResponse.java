package com.fran.response;

import lombok.Data;

@Data
public class OrderDriverResponse {
    private Long carId;
    private Long driverId;
    private String driverPhone;
    /**
     * 机动车驾驶证号
     */
    private String licenseId;

    /**
     * 车辆号牌
     */
    private String vehicleNo;
    private String vehicleType;

}
