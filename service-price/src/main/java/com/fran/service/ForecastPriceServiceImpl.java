package com.fran.service;

import com.fran.constant.CommonStatusEnum;
import com.fran.dto.CommonResult;
import com.fran.mapper.PriceRuleMapper;
import com.fran.pojo.PriceRule;
import com.fran.remote.ServiceMapClient;
import com.fran.request.ForecastPriceDto;
import com.fran.response.DirectionResponse;
import com.fran.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastPriceService{
    @Autowired
    private ServiceMapClient serviceMapClient;
    @Autowired
    private PriceRuleMapper priceRuleMapper;

    @Override
    public CommonResult forecastPrice(String depLongitude,String depLatitude,String destLongitude,String destLatitude) {
        log.info("============service-price===========");
        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度："+ destLatitude);

        log.info("调用地图服务，查询距离和时长");
        ForecastPriceDto forecastPriceDto = new ForecastPriceDto();
        forecastPriceDto.setDepLongitude(depLongitude);
        forecastPriceDto.setDepLatitude(depLatitude);
        forecastPriceDto.setDestLongitude(destLongitude);
        forecastPriceDto.setDestLatitude(destLatitude);
        CommonResult<DirectionResponse> driving = serviceMapClient.driving(forecastPriceDto);
        if(driving.getCode()!= CommonStatusEnum.SUCCESS.getCode()){
            return CommonResult.fail("请求失败！");
        }
        Integer distance = driving.getData().getDistance();
        log.info("距离" + distance + "米");
        Integer duration = driving.getData().getDuration();
        log.info("时长" + duration + "分钟");


        log.info("读取计价规则");
        Map<String,Object> map = new HashMap<>();
        map.put("city_code","110000");
        map.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(map);
        if(priceRules.size()==0){
            return CommonResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode()
                    ,CommonStatusEnum.PRICE_RULE_EMPTY.getMessage());
        }
        PriceRule priceRule = priceRules.get(0);


        log.info("计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(11.11);
        return CommonResult.success(forecastPriceResponse);
    }
}
