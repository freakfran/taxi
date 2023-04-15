package com.fran.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SSEController {
    private static Map<String,SseEmitter> sseEmitterMap = new HashMap<>();


    @GetMapping("/connect/{driverId}")
    public SseEmitter connect(@PathVariable("driverId")String driverId){
        System.out.println("司机id："+driverId);
        //0：连接永不超时
        SseEmitter sseEmitter = new SseEmitter(0l);
        //连接一建立就存储连接
        sseEmitterMap.put(driverId,sseEmitter);
        return sseEmitter;
    }


    //发送消息
    @GetMapping("/push")
    public String push(@RequestParam("driverId") String driverId,@RequestParam("message") String message){
        try {
            if(sseEmitterMap.containsKey(driverId)){
                SseEmitter sseEmitter = sseEmitterMap.get(driverId);
                sseEmitter.send(message);
            }else {
                return "用户不存在";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "给" + driverId + "发送了：" + message;
    }

    //关闭连接
    @GetMapping("/close/{driverId}")
    public String close(@PathVariable("driverId")String driverId){
        if(sseEmitterMap.containsKey(driverId)){
            sseEmitterMap.remove(driverId);
        }
        return "关闭成功";
    }
}
