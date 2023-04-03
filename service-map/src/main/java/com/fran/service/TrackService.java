package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.response.TrackResponse;

public interface TrackService {
    public CommonResult<TrackResponse> add(Integer tid);
}
