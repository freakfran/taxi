package com.fran.controller;

import com.fran.util.SsePrefixUtils;
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


    @GetMapping("/connect")
    public SseEmitter connect(@RequestParam("userId")String userId,@RequestParam("identity")String identity){
        System.out.println("建立连接userId="+userId+",identity="+identity);
        //0：连接永不超时
        SseEmitter sseEmitter = new SseEmitter(0l);
        //连接一建立就存储连接
        sseEmitterMap.put(SsePrefixUtils.generateSseKey(userId,identity),sseEmitter);
        return sseEmitter;
    }


    //发送消息
    @GetMapping("/push")
    public String push(@RequestParam("userId")String userId,@RequestParam("identity")String identity,@RequestParam("message") String message){
        System.out.println("发送消息userId="+userId+",identity="+identity);
        String sseKey = SsePrefixUtils.generateSseKey(userId,identity);
        try {
            if(sseEmitterMap.containsKey(sseKey)){
                SseEmitter sseEmitter = sseEmitterMap.get(sseKey);
                sseEmitter.send(message);
            }else {
                return "用户不存在";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "给" + userId + "发送了：" + message;
    }

    //关闭连接
    @GetMapping("/close")
    public String close(@RequestParam("userId")String userId,@RequestParam("identity")String identity){
        String sseKey = SsePrefixUtils.generateSseKey(userId,identity);
        if(sseEmitterMap.containsKey(sseKey)){
            sseEmitterMap.remove(sseKey);
        }
        return "关闭成功";
    }
}
