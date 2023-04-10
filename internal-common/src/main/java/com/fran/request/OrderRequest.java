package com.fran.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private String address;
    private String deLatitude;
    private String deLongitude;
    private LocalDateTime departTime;
    private String departure;
    private String desLatitude;
    private String desLongitude;
    private String destination;
    //坐标加密标识
    private Integer encrypt;
    private String fareType;
    private LocalDateTime orderTime;
}
