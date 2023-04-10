package com.fran.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fran.dto.CommonResult;
import com.fran.mapper.PriceRuleMapper;
import com.fran.pojo.PriceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceRuleServiceImpl implements PriceRuleService{
    @Autowired
    private PriceRuleMapper priceRuleMapper;

    @Override
    public CommonResult add(PriceRule priceRule) {
        String fareType = priceRule.getCityCode() + priceRule.getVehicleType();
        priceRule.setFareType(fareType);

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",priceRule.getCityCode());
        queryWrapper.eq("vehicle_type",priceRule.getVehicleType());
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        int version = 0;
        //找最大的版本号
        if(!priceRules.isEmpty()){
            version = priceRules.get(0).getFareVersion() + 1;
        }
        priceRule.setFareVersion(version);

        priceRuleMapper.insert(priceRule);
        return CommonResult.success();
    }
}
