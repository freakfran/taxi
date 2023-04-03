package com.fran.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fran.constant.AMapConstants;
import com.fran.dto.CommonResult;
import com.fran.request.PointRequest;
import com.fran.request.PointsDTO;
import com.fran.response.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PointClient {
    @Value("${amap.key}")
    private String amapKey;
    @Value("${amap.sid}")
    private String sid;

    @Autowired
    private RestTemplate restTemplate;

    public CommonResult upload(PointRequest pointRequest) {
        //拼接url
        //https://tsapi.amap.com/v1/track/terminal/add
        StringBuilder url = new StringBuilder();
        url.append(AMapConstants.POINT_UPLOAD_URL);
        //resttemplate会把{}当占位符
        String points = JSON.toJSONString(pointRequest.getPoints());
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("key",amapKey);
        map.add("sid",sid);
        map.add("tid",pointRequest.getTid().toString());
        map.add("trid",pointRequest.getTrid().toString());
        map.add("points",points);
        log.info(url.toString());

        ResponseEntity<String> entity = restTemplate.postForEntity(url.toString(),map, String.class);
        String body = entity.getBody();
        //System.out.println(body);
        JSONObject jsonObject = JSON.parseObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        if(data==null){
            return CommonResult.fail(jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
        }


        return CommonResult.success();
    }
}
