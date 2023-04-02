package com.fran.response;

import lombok.Data;

@Data
public class DriverUserExistsResponse {
    String driverPhone;
    int isExists;
}
