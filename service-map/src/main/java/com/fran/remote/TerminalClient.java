package com.fran.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fran.constant.AMapConstants;
import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TerminalClient {
    @Value("${amap.key}")
    private String amapKey;
    @Value("${amap.sid}")
    private String sid;


    @Autowired
    private RestTemplate restTemplate;

    public CommonResult<TerminalResponse> add(String name,String desc){
        //拼接url
        //https://tsapi.amap.com/v1/track/terminal/add
        StringBuilder url = new StringBuilder();
        url.append(AMapConstants.TERMINAL_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + sid);
        url.append("&name=" + name);
        url.append("&desc=" + desc);
        log.info(url.toString());

        ResponseEntity<String> entity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = entity.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        if(data==null){
            return CommonResult.fail(jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
        }
        Long tid = data.getLong("tid");
        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);
        return CommonResult.success(terminalResponse);
    }

    public CommonResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {
        //拼接url
        //https://tsapi.amap.com/v1/track/terminal/add
        StringBuilder url = new StringBuilder();
        url.append(AMapConstants.AROUND_SEARCH_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + sid);
        url.append("&center=" + center);
        url.append("&radius=" + radius);
        log.info(url.toString());

        ResponseEntity<String> entity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = entity.getBody();

        //解析data
        JSONObject bodyJson = JSON.parseObject(body);
        JSONObject data = bodyJson.getJSONObject("data");
        JSONArray results = data.getJSONArray("results");
        log.info(body);
        if(data==null){
            return CommonResult.fail(bodyJson.getInteger("errcode"),bodyJson.getString("errmsg"));
        }

        List<TerminalResponse> list = new ArrayList<>();
        for(int i = 0;i < results.size();i++){
            JSONObject jsonObject = results.getJSONObject(i);
            String carId = jsonObject.getString("desc");
            Long tid = jsonObject.getLong("tid");
            JSONObject location = jsonObject.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");
            TerminalResponse terminalResponse = new TerminalResponse();
            terminalResponse.setTid(tid);
            terminalResponse.setCarId(carId);
            terminalResponse.setLatitude(latitude);
            terminalResponse.setLongitude(longitude);
            list.add(terminalResponse);
        }

        return CommonResult.success(list);
    }
}
