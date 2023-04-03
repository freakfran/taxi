package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import com.fran.response.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-map")
public interface ServiceMapClient {
    @PostMapping("/terminal/add")
    public CommonResult<TerminalResponse> addTerminal(@RequestParam("name") String name,@RequestParam("desc")String desc);

    @PostMapping("/track/add")
    public CommonResult<TrackResponse> addTrack(@RequestParam("tid")Integer tid);
}
