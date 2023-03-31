package com.fran.service;

import com.fran.constant.AMapConstants;
import com.fran.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService{
    @Value("${amap.key}")
    private String amapKey;

    @Override
    public CommonResult initDicDistrict(String keywords) {
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
        log.info(url.toString());

        return CommonResult.success();
    }
}
