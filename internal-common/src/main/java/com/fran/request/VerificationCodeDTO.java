package com.fran.request;

import lombok.Data;

@Data
public class VerificationCodeDTO {
    private String passengerPhone;

    private String driverPhone;

    private String verificationCode;
}
