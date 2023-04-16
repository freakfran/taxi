package com.fran.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
public class OrderRequest {
    private Long orderId;
    private Long passengerId;
    private String passengerPhone;
    private String address;
    private String depLatitude;
    private String depLongitude;
    private LocalDateTime departTime;
    private String departure;
    private String desLatitude;
    private String desLongitude;
    private String destination;
    //坐标加密标识
    private Integer encrypt;
    private String fareType;
    private Integer fareVersion;
    private LocalDateTime orderTime;
    private String deviceCode;

    /**
     * 司机去接乘客出发时间
     */
    private LocalDateTime toPickUpPassengerTime;

    /**
     * 去接乘客时，司机的经度
     */
    private String toPickUpPassengerLongitude;

    /**
     * 去接乘客时，司机的纬度
     */
    private String toPickUpPassengerLatitude;

    /**
     * 去接乘客时，司机的地点
     */
    private String toPickUpPassengerAddress;

    /**
     * 接到乘客，乘客上车经度
     */
    private String pickUpPassengerLongitude;

    /**
     * 接到乘客，乘客上车纬度
     */
    private String pickUpPassengerLatitude;

}
