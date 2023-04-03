package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.request.ApiDriverPointRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface PointService {
    public CommonResult upload(ApiDriverPointRequest apiDriverPointRequest);
}
