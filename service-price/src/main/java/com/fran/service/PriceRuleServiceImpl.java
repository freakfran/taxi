package com.fran.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fran.constant.CommonStatusEnum;
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
        String fareType = priceRule.getCityCode() + "$" + priceRule.getVehicleType();
        priceRule.setFareType(fareType);

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",priceRule.getCityCode());
        queryWrapper.eq("vehicle_type",priceRule.getVehicleType());
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        int version = 0;
        //找最大的版本号
        if(!priceRules.isEmpty()){
            return CommonResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(),CommonStatusEnum.PRICE_RULE_EXISTS.getMessage());
        }
        priceRule.setFareVersion(++version);

        priceRuleMapper.insert(priceRule);
        return CommonResult.success();
    }

    @Override
    public CommonResult edit(PriceRule priceRule) {
        String fareType = priceRule.getCityCode() + "$" + priceRule.getVehicleType();
        priceRule.setFareType(fareType);

        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",priceRule.getCityCode());
        queryWrapper.eq("vehicle_type",priceRule.getVehicleType());
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        int version = 0;
        //找最大的版本号
        if(!priceRules.isEmpty()){
            PriceRule lastPriceRule = priceRules.get(0);
            Double unitPricePerMile = lastPriceRule.getUnitPricePerMile();
            Double unitPricePerMinute = lastPriceRule.getUnitPricePerMinute();
            Double startFare = lastPriceRule.getStartFare();
            Integer startMile = lastPriceRule.getStartMile();
            if(unitPricePerMile.doubleValue() == priceRule.getUnitPricePerMile()
            && unitPricePerMinute.doubleValue() == priceRule.getUnitPricePerMinute()
            &&startFare.doubleValue() == priceRule.getStartFare()
            &&startMile.doubleValue() == priceRule.getStartMile()){
                return CommonResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(),CommonStatusEnum.PRICE_RULE_EXISTS.getMessage());
            }
            version = lastPriceRule.getFareVersion()+1;
        }
        priceRule.setFareVersion(version);

        priceRuleMapper.insert(priceRule);
        return CommonResult.success();
    }

    @Override
    public CommonResult<PriceRule> getLatest(String fareType) {
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fare_type",fareType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if(priceRules.isEmpty()){
            return CommonResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getMessage());
        }
        return CommonResult.success(priceRules.get(0));
    }

    @Override
    public CommonResult<Boolean> isLatest(String fareType, Integer fareVersion) {
        CommonResult<PriceRule> latest = getLatest(fareType);
        if(latest.getCode() == CommonStatusEnum.PRICE_RULE_EMPTY.getCode()){
            return CommonResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getMessage());
        }
        Integer fareVersionDB = latest.getData().getFareVersion();
        if(fareVersionDB > fareVersion){
            return CommonResult.success(false);
        }
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<Boolean> isExists(PriceRule priceRule) {
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode);
        queryWrapper.eq("vehicle_type",vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        return priceRules.isEmpty() ? CommonResult.success(false) : CommonResult.success(true);
    }
}
