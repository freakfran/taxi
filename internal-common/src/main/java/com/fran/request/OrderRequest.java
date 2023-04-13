package com.fran.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
public class OrderRequest {
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
}
