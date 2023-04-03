package com.fran.request;

import lombok.Data;

@Data
public class PointRequest {
    private Integer tid;
    private Integer trid;
    private PointsDTO[] points;
}
