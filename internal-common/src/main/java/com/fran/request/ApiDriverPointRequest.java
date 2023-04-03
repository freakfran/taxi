package com.fran.request;

import lombok.Data;

@Data
public class ApiDriverPointRequest {
    private Long carId;
    private PointsDTO[] points;
}
