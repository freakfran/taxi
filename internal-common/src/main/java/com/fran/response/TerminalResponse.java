package com.fran.response;

import lombok.Data;

@Data
public class TerminalResponse {
    private Long tid;
    private String carId;
    private String longitude;
    private String latitude;
}
