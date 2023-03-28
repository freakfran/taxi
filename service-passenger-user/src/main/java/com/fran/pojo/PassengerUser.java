package com.fran.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassengerUser {
    private Long id;
    private String passengerPhone;
    private String passengerName;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Byte passengerGender;
    private Byte state;
}
