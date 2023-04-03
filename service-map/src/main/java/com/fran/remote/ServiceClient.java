package com.fran.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fran.constant.AMapConstants;
import com.fran.constant.CommonStatusEnum;
import com.fran.dto.CommonResult;
import com.fran.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ServiceClient {
    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public CommonResult add(String name){
        //拼接url
        //https://tsapi.amap.com/v1/track/service/add
        StringBuilder url = new StringBuilder();
        url.append(AMapConstants.SERVICE_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&name=" + name);
        log.info(url.toString());
        //调用接口
        ResponseEntity<String> entity = restTemplate.postForEntity(url.toString(),null, String.class);
        String body = entity.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        //{
        //    "data": {
        //        "name": "飞滴出行service",
        //        "sid": 914418
        //    },
        //    "errcode": 10000,
        //    "errdetail": null,
        //    "errmsg": "OK"
        //}
        JSONObject data = jsonObject.getJSONObject("data");
        if(data==null){
            return CommonResult.fail(CommonStatusEnum.SERVICE_EXISTS.getCode(),CommonStatusEnum.SERVICE_EXISTS.getMessage());
        }
        Integer sid = data.getInteger("sid");
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSid(sid);
        return CommonResult.success(serviceResponse);
    }
}
