package com.fran.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fran.constant.AMapConstants;
import com.fran.dto.CommonResult;
import com.fran.response.TerminalResponse;
import com.fran.response.TrackResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TrackClient {
    @Value("${amap.key}")
    private String amapKey;
    @Value("${amap.sid}")
    private String sid;
    @Autowired
    private RestTemplate restTemplate;

    public CommonResult<TrackResponse> add(Integer tid){
        //拼接url
        //https://tsapi.amap.com/v1/track/trace/add
        StringBuilder url = new StringBuilder();
        url.append(AMapConstants.TRACK_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&sid=" + sid);
        url.append("&tid=" + tid);
        log.info(url.toString());

        ResponseEntity<String> entity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = entity.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        if(data==null){
            return CommonResult.fail(jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
        }
        Integer trid = data.getInteger("trid");
        String trname = data.getString("trname");
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setTrid(trid);
        trackResponse.setTrname(trname);
        return CommonResult.success(trackResponse);
    }
}
