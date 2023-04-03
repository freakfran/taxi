package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.PointRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface PointService {

    public CommonResult upload(PointRequest pointRequest);
}
