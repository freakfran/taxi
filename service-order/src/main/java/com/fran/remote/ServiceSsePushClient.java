package com.fran.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-sse-push")
public interface ServiceSsePushClient {
    @GetMapping("/push")
    public String push(@RequestParam("userId")String userId, @RequestParam("identity")String identity, @RequestParam("message") String message);
}
