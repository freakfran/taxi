package com.fran.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fran.constant.AMapConstants;
import com.fran.constant.CommonStatusEnum;
import com.fran.dto.CommonResult;
import com.fran.mapper.DicDistrictMapper;
import com.fran.pojo.DicDistrict;
import com.fran.remote.AMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService{
    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private AMapClient aMapClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    @Override
    public CommonResult initDicDistrict(String keywords) {
        //请求地图
        String data = aMapClient.initDicDistrict(keywords);
        //解析结果
        JSONObject districtJsonObject = JSONObject.parseObject(data);
        Integer status = districtJsonObject.getInteger("status");
        if(status==null||status!=1){
            return CommonResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(),CommonStatusEnum.MAP_DISTRICT_ERROR.getMessage());
        }
        JSONArray districts = districtJsonObject.getJSONArray("districts");
        for (int i = 0; i < districts.size(); i++) {
            JSONObject countryDistrictJson = districts.getJSONObject(i);

            String countryAddressCode = countryDistrictJson.getString("adcode");
            String countryAddressName = countryDistrictJson.getString("name");
            String countryParentAddressCode = "0";
            String countryLevel = countryDistrictJson.getString("level");

            insertDistrict(countryAddressCode,countryAddressName,countryParentAddressCode,countryLevel);

            JSONArray provinceDistricts = countryDistrictJson.getJSONArray("districts");

            for(int j = 0;j < provinceDistricts.size();j++){
                JSONObject provinceDistrictJson = provinceDistricts.getJSONObject(j);

                String provinceAddressCode = provinceDistrictJson.getString("adcode");
                String provinceAddressName = provinceDistrictJson.getString("name");
                String provinceParentAddressCode = countryAddressCode;
                String provinceLevel = provinceDistrictJson.getString("level");

                insertDistrict(provinceAddressCode,provinceAddressName,provinceParentAddressCode,provinceLevel);

                JSONArray cityDistricts = provinceDistrictJson.getJSONArray("districts");

                for(int k = 0;k < cityDistricts.size();k++){
                    JSONObject cityDistrictJson = cityDistricts.getJSONObject(k);

                    String cityAddressCode = cityDistrictJson.getString("adcode");
                    String cityAddressName = cityDistrictJson.getString("name");
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityDistrictJson.getString("level");

                    insertDistrict(cityAddressCode,cityAddressName,cityParentAddressCode,cityLevel);

                    JSONArray districtDistricts = cityDistrictJson.getJSONArray("districts");

                    for(int p = 0;p < districtDistricts.size();p++){
                        JSONObject districtDistrictJson = districtDistricts.getJSONObject(p);

                        String districtAddressCode = districtDistrictJson.getString("adcode");
                        String districtAddressName = districtDistrictJson.getString("name");
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtDistrictJson.getString("level");

                        if(districtLevel.trim().equals("street"))continue;

                        insertDistrict(districtAddressCode,districtAddressName,districtParentAddressCode,districtLevel);

                    }
                }
            }
        }








        return CommonResult.success();
    }


    private void insertDistrict(String addressCode,String addressName,String parentAddressCode,String level){
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);
        dicDistrict.setParentAddressCode(parentAddressCode);
        dicDistrict.setLevel(parseLevel(level));
        System.out.println(dicDistrict.toString());
        dicDistrictMapper.insert(dicDistrict);
    }


    private int parseLevel(String level){
        int levelInt = 0;
        if(level.trim().equals("country")){
            levelInt = 0;
        }else if(level.trim().equals("province")){
            levelInt = 1;
        }else if(level.trim().equals("city")){
            levelInt = 2;
        }else if(level.trim().equals("district")){
            levelInt = 3;
        }

        return levelInt;
    }
}
