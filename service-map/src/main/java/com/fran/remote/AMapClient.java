package com.fran.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fran.constant.AMapConstants;
import com.fran.dto.CommonResult;
import com.fran.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//调用高德的api
@Service
@Slf4j
public class AMapClient {
    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    public DirectionResponse driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //组装URL  https://restapi.amap.com/v3/direction/driving
        // ?origin=116.45925,39.910031&destination=116.587922,40.081577&output=json&key=ba81aa920fc583b0c5e5180c82bba1bc
        StringBuilder url = new StringBuilder();
        url.append(AMapConstants.AMAP_URL);
        url.append("?");
        url.append("origin=" + depLongitude + "," + depLatitude);
        url.append("&destination=" + destLongitude + "," + destLatitude);
        url.append("&output=json&key=" + amapKey);
        log.info(url.toString());
        //调用接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(url.toString(), String.class);
        //解析结果
        DirectionResponse directionResponse = parseDirectionEntity(directionEntity);
        return directionResponse;
    }



    private DirectionResponse parseDirectionEntity(ResponseEntity<String> directionEntity){
        DirectionResponse directionResponse = null;
        try {
            JSONObject jsonObject = JSON.parseObject(directionEntity.getBody());
            Integer status = jsonObject.getInteger("status");
            if(status!=null){
                if(status==1){
                    JSONObject route = jsonObject.getJSONObject("route");
                    if(route!=null){
                        JSONArray paths = route.getJSONArray("paths");
                        JSONObject res =  paths.getJSONObject(0);
                        Integer distance = res.getInteger("distance");
                        Integer duration = res.getInteger("duration");
                        if(distance!=null&&duration!=null){
                            directionResponse = new DirectionResponse();
                            directionResponse.setDistance(distance);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }
        }catch (Exception e){
        }
        return directionResponse;
    }


    public String initDicDistrict(String keywords) {
        //https://restapi.amap.com/v3/config/district?
        // keywords=北京&subdistrict=2&key=ba81aa920fc583b0c5e5180c82bba1bc
        //拼装URL
        StringBuilder url = new StringBuilder();
        url.append(AMapConstants.AMAP_DISTRICT_URL);
        url.append("?");
        url.append("keywords="+keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key=" + amapKey);

        //请求结果
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);

        return forEntity.getBody();

    }
}
